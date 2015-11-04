package me.parimal.parth.guarded_ridge.models;

import java.util.UUID;

/**
 * Created by parth on 23/6/15.
 */
public class Bookmark {

  private String _id;
  private String title;
  private String link;
  private String tags;
  private long createdTs;
  private boolean active = true;

  public Bookmark() {}

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public long getCreatedTs() {
    return createdTs;
  }

  public void setCreatedTs(long createdTs) {
    this.createdTs = createdTs;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }



  @Override
  public String toString() {
    return "Bookmark{" +
        "title='" + title + '\'' +
        ", link='" + link + '\'' +
        ", tags='" + tags + '\'' +
        ", createdTs=" + createdTs +
        ", active=" + active +
        '}';
  }
}
