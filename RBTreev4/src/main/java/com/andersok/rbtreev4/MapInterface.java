
package com.andersok.rbtreev4;

public interface MapInterface <K extends Comparable<K>, V> {
  public void setValue (K key, V value);
  public V getValue (K key);
}

