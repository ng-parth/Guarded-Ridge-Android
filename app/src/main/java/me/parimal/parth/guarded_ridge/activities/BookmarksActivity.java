package me.parimal.parth.guarded_ridge.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import me.parimal.parth.guarded_ridge.fragments.BookmarksActivityFragment;
import me.parimal.parth.guarded_ridge.R;
import me.parimal.parth.guarded_ridge.fragments.UpsertBookmarkFragment;
import me.parimal.parth.guarded_ridge.services.ChangeFragment;


public class BookmarksActivity extends Activity implements ChangeFragment {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bookmarks);
    FragmentManager fm = getFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    BookmarksActivityFragment bookmarksActivityFragment = new BookmarksActivityFragment();
    ft.replace(R.id.bmListFragment, bookmarksActivityFragment).commit();
//    Toast.makeText(BookmarksActivity.this, "In BMActivity", Toast.LENGTH_LONG).show();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_bookmarks, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onChangeFragmentListener(String id, String src) {
//    Toast.makeText(getApplicationContext(), "Changing fragment from activity. Id = "+id, Toast.LENGTH_SHORT).show();
    FragmentTransaction ft = getFragmentManager().beginTransaction();
    if (!id.isEmpty() && "BookmarkList".equals(src)) {
      UpsertBookmarkFragment upsertBookmarkFragment= new UpsertBookmarkFragment();
      Bundle bundle = new Bundle();
      bundle.putString("_id", id);
      upsertBookmarkFragment.setArguments(bundle);
      ft.replace(R.id.bmListFragment, upsertBookmarkFragment)
          .addToBackStack("bookmarkList")
          .commit();
    }

  }

}
