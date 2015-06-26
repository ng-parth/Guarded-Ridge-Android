package me.parimal.parth.guarded_ridge.services;

import android.widget.ListView;

import java.util.List;

import me.parimal.parth.guarded_ridge.models.Bookmark;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by parth on 23/6/15.
 */
public interface BookmarkService {
  public String BASE_URL = "https://guarded-ridge-6883.herokuapp.com";
  public String URL = "/api/bookmark";

//  @GET(URL)
//  List<Bookmark> getBookmarks();
  @GET(URL)
  public void getBookmarks(Callback<List<Bookmark>> bookmarks);

}