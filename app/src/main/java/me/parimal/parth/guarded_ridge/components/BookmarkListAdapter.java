package me.parimal.parth.guarded_ridge.components;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.parimal.parth.guarded_ridge.R;
import me.parimal.parth.guarded_ridge.activities.BookmarksActivity;
import me.parimal.parth.guarded_ridge.fragments.BookmarksActivityFragment;
import me.parimal.parth.guarded_ridge.models.Bookmark;

/**
 * Created by parth on 22/6/15.
 */
public class BookmarkListAdapter extends ArrayAdapter<Bookmark> {
  private final int resource;
  private final Context context;

  public BookmarkListAdapter(Context context, int resource, List<Bookmark> objects) {
    super(context, resource, objects);
    this.resource = resource;
    this.context = context;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View v = convertView;
    if(v == null) {
      LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      v = vi.inflate(resource, null);
    }
    final Bookmark bm = getItem(position);
    TextView firstLine = (TextView) v.findViewById(R.id.firstLine);
    TextView secondLine = (TextView) v.findViewById(R.id.secondLine);
    ImageView editIcon = (ImageView) v.findViewById(R.id.editIcon);
    editIcon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View innerV) {
        if(context instanceof BookmarksActivity) {
          ((BookmarksActivity) context).onChangeFragmentListener(bm.get_id(), "BookmarkList");
        }
      }
    });
    firstLine.setText(bm.getTitle());
    secondLine.setText(bm.getLink());
    return v;
  }

}
