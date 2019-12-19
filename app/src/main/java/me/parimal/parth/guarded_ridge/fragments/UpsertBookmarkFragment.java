package me.parimal.parth.guarded_ridge.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import me.parimal.parth.guarded_ridge.R;
import me.parimal.parth.guarded_ridge.models.Bookmark;
import me.parimal.parth.guarded_ridge.services.BookmarkService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpsertBookmarkFragment extends Fragment {


  public UpsertBookmarkFragment() {
    // Required empty public constructor
  }

  String _id = null;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_upsert_bookmark, container, false);
    final String BASE_URL = "https://save-my-links.herokuapp.com";
    final EditText editTitle = (EditText) view.findViewById(R.id.editTitle);
    final EditText editLink = (EditText) view.findViewById(R.id.editLink);
    final EditText editTag = (EditText) view.findViewById(R.id.editTag);
    final Button updateBtn = (Button) view.findViewById(R.id.upsertBookmarkBtn);
    final RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(BASE_URL)
        .build();
    final BookmarkService bookmarkService = restAdapter.create(BookmarkService.class);

    Bundle bundle = getArguments();
    if (bundle != null) {
      _id = bundle.getString("_id");
    }

    if (_id != null) {


      bookmarkService.getBookmark(_id, new Callback<Bookmark>() {
        @Override
        public void success(Bookmark bookmark, Response response) {
          editTitle.setText(bookmark.getTitle());
          editLink.setText(bookmark.getLink());
          editTag.setText(bookmark.getTags());
          updateBtn.setText("Update Bookmark");
        }

        @Override
        public void failure(RetrofitError error) {
          Log.e("Service Error- ", "@UpsertBookmarkFragment :" + error.getMessage());
          Toast.makeText(getActivity().getApplicationContext(), "Failed to load bookmark. Try again later.", Toast.LENGTH_SHORT).show();
        }
      });
    }

    updateBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (IsFormValid(editTitle, editLink)) {
          Bookmark bookmark = new Bookmark();
          bookmark.setLink(editLink.getText().toString());
          bookmark.setTitle(editTitle.getText().toString());
          bookmark.setTags(editTag.getText().toString());
          if (_id != null) {
            // Update
            bookmarkService.putBookmark(_id, bookmark, new Callback<String>() {
              @Override
              public void success(String s, Response response) {
                if (s == "true") {
                  Toast.makeText(getActivity().getApplicationContext(), "Bookmark Updated!", Toast.LENGTH_SHORT).show();
                }
              }

              @Override
              public void failure(RetrofitError error) {
                Log.e("Error: ", error.getMessage());
              }
            });
          } else {
            // Post
            bookmark.setCreatedTs(new Date().getTime());
          }
        }
      }
    });

    return view;
  }

  public boolean IsFormValid(EditText editTitle, EditText editLink) {
    if (editTitle.getText().length() == 0) {
      Toast.makeText(getActivity().getApplicationContext(), "Please add title.", Toast.LENGTH_LONG).show();
      return false;
    }
    if (editLink.getText().length() == 0) {
      Toast.makeText(getActivity().getApplicationContext(), "Please add link.", Toast.LENGTH_LONG).show();
      return false;
    }
    return true;
  }

}
