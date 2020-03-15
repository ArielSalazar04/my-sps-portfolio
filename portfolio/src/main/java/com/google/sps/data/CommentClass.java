
package com.google.sps.data;

public final class CommentClass {

  private final long id;
  private final String text;

  public CommentClass(long id, String text) {
    this.id = id;
    this.text = text;
  }
  public long getId(){
      return this.id;
  }
  public String getText(){
      return this.text;
  }
}