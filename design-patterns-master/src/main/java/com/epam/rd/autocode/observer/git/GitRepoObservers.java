package com.epam.rd.autocode.observer.git;

import java.util.*;

public class GitRepoObservers {
    public static Repository newRepository(){
         final List<WebHook> webHooks = new ArrayList<>();
         final Map<String, List<Commit>> branches = new HashMap<>();
        return new Repository() {
            @Override
            public void addWebHook(WebHook webHook) {
                webHooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Commit commit = new Commit(author, changes);
                branches.putIfAbsent(branch, new ArrayList<>());
                branches.get(branch).add(commit);
                Event event = new Event(Event.Type.COMMIT, branch, List.of(commit));
                notifyWebHooks(event);

                return commit;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                List<Commit> sourceCommits = branches.getOrDefault(sourceBranch, Collections.emptyList());
                branches.putIfAbsent(targetBranch, new ArrayList<>());
                List<Commit> targetCommits = branches.get(targetBranch);

                Set<Commit> targetCommitSet = new HashSet<>(targetCommits);
                List<Commit> newCommits = new ArrayList<>();
                for (Commit commit : sourceCommits) {
                    if (!targetCommitSet.contains(commit)) {
                        newCommits.add(commit);
                        targetCommits.add(commit);
                    }
                }
                if (!newCommits.isEmpty()) {
                    Event event = new Event(Event.Type.MERGE, targetBranch, newCommits);
                    notifyWebHooks(event);
                }
            }
            private void notifyWebHooks(Event event) {
                for (WebHook webHook : webHooks) {
                    if (webHook.branch().equals(event.branch())) {
                        webHook.onEvent(event);
                    }
                }
            }
        };
    }

    public static WebHook mergeToBranchWebHook(String branchName){
         final List<Event> caughtEvents = new ArrayList<>();
        return new WebHook() {
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.MERGE;
            }

            @Override
            public List<Event> caughtEvents() {
                return caughtEvents;
            }

            @Override
            public void onEvent(Event event) {
                if (event.type() == Event.Type.MERGE && event.branch().equals(branchName)) {
                    caughtEvents.add(event);
                }
            }

        };
    }

    public static WebHook commitToBranchWebHook(String branchName){
         final List<Event> caughtEvents = new ArrayList<>();
        return new WebHook() {
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.COMMIT;
            }

            @Override
            public List<Event> caughtEvents() {
                return caughtEvents;
            }

            @Override
            public void onEvent(Event event) {
                if (event.type() == Event.Type.COMMIT && event.branch().equals(branchName)) {
                    caughtEvents.add(event);
                }
            }
            };
        }
}

