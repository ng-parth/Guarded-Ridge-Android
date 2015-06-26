package me.parimal.parth.guarded_ridge.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import me.parimal.parth.guarded_ridge.R;
import me.parimal.parth.guarded_ridge.models.Bookmark;

/**
 * Created by parth on 22/6/15.
 */
public class BookmarkListAdapter extends ArrayAdapter<Bookmark> {
  private final int resource;

  public BookmarkListAdapter(Context context, int resource, List<Bookmark> objects) {
    super(context, resource, objects);
    this.resource = resource;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View v = convertView;
    if(v == null) {
      LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      v = vi.inflate(resource, null);
    }
    Bookmark bm = getItem(position);
    TextView firstLine = (TextView) v.findViewById(R.id.firstLine);
    TextView secondLine = (TextView) v.findViewById(R.id.secondLine);
    firstLine.setText(bm.getTitle());
    secondLine.setText(bm.getLink());
    return v;
  }

}
