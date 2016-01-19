package demo.controller;

import java.util.Set;

/**
 * Created by randyb on 1/18/2016.
 */
public class MockOneDrive implements IOneDrive{
   @Override
   public Set<String> getRootFolderList(String token) {
      return null;
   }

   @Override
   public Set<String> getFilesInFolderList(String folder, String token) {
      return null;
   }

   @Override
   public Set<String> getFoldersInFolderList(String folder, String token) {
      return null;
   }

   @Override
   public Object getDocument(String documentID, String token) {
      return null;
   }

   @Override
   public void updateDocument(String documentID, Object content, String token) {

   }

   @Override
   public void deleteDocument(String documentID, String token) {

   }
}
