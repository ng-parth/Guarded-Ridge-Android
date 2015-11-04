package me.parimal.parth.guarded_ridge.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.parimal.parth.guarded_ridge.R;
import me.parimal.parth.guarded_ridge.components.BookmarkListAdapter;
import me.parimal.parth.guarded_ridge.models.Bookmark;
import me.parimal.parth.guarded_ridge.services.BookmarkService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.POST;


/**
 * A placeholder fragment containing a simple view.
 */
public class BookmarksActivityFragment extends Fragment {

  public BookmarksActivityFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);
    final ListView bmListView = (ListView) view.findViewById(R.id.listview);
    final List<Bookmark> bmList = new ArrayList<>();
    final String BASE_URL = "https://guarded-ridge-6883.herokuapp.com";
    final BookmarkListAdapter bookmarkListAdapter;
    bookmarkListAdapter = new BookmarkListAdapter(getActivity(), R.layout.layout_bookmarks, bmList );
    bmListView.setAdapter(bookmarkListAdapter);

//    bmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      @Override
//      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Bookmark bm = bmList.get(position);
//        Toast.makeText(getActivity().getApplicationContext(), "Link " + (position + 1) + ": " + bm.getLink(), Toast.LENGTH_SHORT).show();
//      }
//    });


//    bmListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//      @Override
//      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity().getApplicationContext(), "I wish to add a context menu.", Toast.LENGTH_SHORT).show();
//        return false;
//      }
//    });

    RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(BASE_URL)
        .build();
    BookmarkService bookmarkService = restAdapter.create(BookmarkService.class);
    bookmarkService.getBookmarks(new Callback<List<Bookmark>>() {
      @Override
      public void success(List<Bookmark> bookmarks, Response response) {
//        Toast.makeText(getActivity().getApplicationContext(),bookmarks.size() +  " Bookmarks fetched", Toast.LENGTH_SHORT).show();
        bmList.addAll(bookmarks);
        bookmarkListAdapter.notifyDataSetChanged();
      }

      @Override
      public void failure(RetrofitError error) {
        Log.e("Service Error- ","@BookmarkActivityFragment :"+ error.getMessage());
        Toast.makeText(getActivity().getApplicationContext(), "Failed to load bookmarks. Try again later.", Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }
}