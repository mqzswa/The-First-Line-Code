package com.example.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {

    private static final int Book_Dir=0;
    private static final int Book_Item=1;
    private static final int Category_Dir=2;
    private static final int Category_Item=3;

    MyDatabaseHelper myDatabaseHelper=null;

    private static final String AUTHORITY="com.example.databasetest.provider";

    private static UriMatcher uriMatcher;

    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",Book_Dir);
        uriMatcher.addURI(AUTHORITY,"book/#",Book_Item);
        uriMatcher.addURI(AUTHORITY,"category",Category_Dir);
        uriMatcher.addURI(AUTHORITY,"category/#",Category_Item);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getReadableDatabase();
        int deleteId=0;
        switch (uriMatcher.match(uri)){
            case Book_Dir:
                deleteId=sqLiteDatabase.delete("book",selection,selectionArgs);
                break;
            case Book_Item:
                String bookId=uri.getPathSegments().get(1);
                deleteId=sqLiteDatabase.delete("book","id=?",new String[]{bookId});
                break;
            case Category_Dir:
                deleteId=sqLiteDatabase.delete("category",null,null);
                break;
            case Category_Item:
                String categoryId=uri.getPathSegments().get(1);
                deleteId=sqLiteDatabase.delete("category","id=?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return deleteId;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");

        switch (uriMatcher.match(uri)){
            case Book_Dir:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book";
            case Book_Item:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.book";
            case Category_Dir:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category";
            case Category_Item:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.category";
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
//        throw new UnsupportedOperationException("Not yet implemented");

        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getReadableDatabase();
        Uri uri_return=null;
        switch (uriMatcher.match(uri)){
            case Book_Dir:
            case Book_Item:
                long bookId=sqLiteDatabase.insert("book",null,values);
                uri_return=Uri.parse("content://"+AUTHORITY+"/book/"+bookId);
                break;

            case Category_Dir:
            case Category_Item:
                long categoryId=sqLiteDatabase.insert("book",null,values);
                uri_return=Uri.parse("content://"+AUTHORITY+"/category/"+categoryId);
                break;

            default:
                break;
        }
        return uri_return;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        myDatabaseHelper=new MyDatabaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case Book_Dir:
                cursor=sqLiteDatabase.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Book_Item:
                String bookId=uri.getPathSegments().get(1);
                cursor=sqLiteDatabase.query("Book",projection,"id=?",new String[]{bookId},null,null,sortOrder);
                break;
            case Category_Dir:
                cursor=sqLiteDatabase.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Category_Item:
                String categoryId=uri.getPathSegments().get(1);
                cursor=sqLiteDatabase.query("Category",projection,"id=?",new String[]{categoryId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");

        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getReadableDatabase();
        int returnId = 0;
        switch (uriMatcher.match(uri)){
            case Book_Dir:
                returnId=sqLiteDatabase.update("book",values,selection,selectionArgs);
                break;
            case  Book_Item:
                String bookId=uri.getPathSegments().get(1);
                sqLiteDatabase.update("book",values,"id=?",new String[]{bookId});
                break;

            case Category_Dir:
                returnId=sqLiteDatabase.update("category",values,selection,selectionArgs);
                break;
            case  Category_Item:
                String categoryId=uri.getPathSegments().get(1);
                sqLiteDatabase.update("category",values,"id=?",new String[]{categoryId});
                break;

            default:
                break;
        }
        return returnId;
    }
}