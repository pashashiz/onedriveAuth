package demo.controller;


import java.util.Set;

public interface IOneDrive {

   Set<String> getRootFolderList(String token);
   Set<String> getFilesInFolderList(String folder, String token);
   Set<String> getFoldersInFolderList(String folder, String token);
   Object getDocument(String documentID, String token);
   void updateDocument(String documentID, Object content, String token);
   void deleteDocument(String documentID, String token);
}
