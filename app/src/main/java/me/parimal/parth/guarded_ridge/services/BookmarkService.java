package me.parimal.parth.guarded_ridge.services;

import android.widget.ListView;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import me.parimal.parth.guarded_ridge.models.Bookmark;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

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

  @GET(URL+"/{id}")
  public void getBookmark(@Path("id") String id, Callback<Bookmark> bookmark);

  @PUT(URL+"/{id}")
  public void putBookmark(@Path("id") String id, @Body Bookmark bookmark, Callback<String> callback);
}