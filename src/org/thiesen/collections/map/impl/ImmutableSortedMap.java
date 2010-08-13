/*
 *  This file is part of Thiesen Collections.
 *  
 *  Copyright (c) 2010 by Marcus Thiesen (marcus@thiesen.org)
 *
 *  Thiesen Collections is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Thiesen Collections is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Thiesen Collections.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thiesen.collections.map.impl;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;

import org.thiesen.collections.collection.impl.CollectionViews;
import org.thiesen.collections.collection.views.IImmutableCollectionView;
import org.thiesen.collections.common.view.map.ImmutableSortedMapView;
import org.thiesen.collections.map.IImmutableSortedMap;
import org.thiesen.collections.map.IMap;
import org.thiesen.collections.map.IMutableSortedMap;
import org.thiesen.collections.map.ISortedMap;
import org.thiesen.collections.map.views.IImmutableSortedMapView;
import org.thiesen.collections.set.impl.SetViews;
import org.thiesen.collections.set.views.IImmutableSetView;

import com.google.common.collect.Iterables;


public class ImmutableSortedMap<K,V> implements IImmutableSortedMap<K, V>, Serializable {

    private static final long serialVersionUID = 2071488170195702205L;
    
    private final com.google.common.collect.ImmutableSortedMap<K, V> _delegate;

    private ImmutableSortedMap( final com.google.common.collect.ImmutableSortedMap<K, V> delegate ) {
        _delegate = delegate;
    }
    
    public static <K,V> ImmutableSortedMap<K,V> copyOf( final Map<? extends K, ? extends V> map ) {
        return new ImmutableSortedMap<K,V>( com.google.common.collect.ImmutableSortedMap.<K, V>copyOf( map ) );
    }
    
    @SuppressWarnings( "unchecked" )
    public static <K,V> ImmutableSortedMap<K,V> copyOf( final IMap<? extends K, ? extends V> map ) {
        if ( map instanceof ImmutableSortedMap<?,?> ) {
            return (ImmutableSortedMap<K, V>) map;
        }
        
        return new ImmutableSortedMap<K,V>( com.google.common.collect.ImmutableSortedMap.<K, V>copyOf( map.asMapView() ) );
    }
    
    @SuppressWarnings( "unchecked" )
    public static <K,V> ImmutableSortedMap<K,V> copyOf( final ISortedMap<? extends K, ? extends V> map ) {
        if ( map instanceof ImmutableSortedMap<?,?> ) {
            return (ImmutableSortedMap<K, V>) map;
        }
        
        return new ImmutableSortedMap<K,V>( com.google.common.collect.ImmutableSortedMap.<K, V>copyOf( map.asMapView() ) );
    }
    
    public static <K,V> ImmutableSortedMap<K,V> copyOf( final Map<? extends K, ? extends V> map, final Comparator<? super K> comparator ) {
        return new ImmutableSortedMap<K,V>( com.google.common.collect.ImmutableSortedMap.<K, V>copyOf( map, comparator ) );
    }
   
    public static <K,V> ImmutableSortedMap<K,V> copyOf( final SortedMap<K, ? extends V> map ) {
        return new ImmutableSortedMap<K,V>( com.google.common.collect.ImmutableSortedMap.<K,V>copyOfSorted( map ) );
    }
    
    public static <K,V> ImmutableSortedMap<K,V> of() {
        return new ImmutableSortedMap<K,V>( com.google.common.collect.ImmutableSortedMap.<K, V>of() );
    }
    
    
    public final void clear() {
        _delegate.clear();
    }

    @Override
    public Comparator<? super K> comparator() {
        return _delegate.comparator();
    }

    @Override
    public boolean containsKey( final Object key ) {
        return _delegate.containsKey( key );
    }

    @Override
    public boolean containsValue( final Object arg0 ) {
        return _delegate.containsValue( arg0 );
    }

    @Override
    public IImmutableSetView<Entry<K, V>> entrySet() {
        return SetViews.asIImmutableSetView( _delegate.entrySet() );
    }

    @Override
    public K firstKey() {
        return _delegate.firstKey();
    }

    @Override
    public V get( final Object arg0 ) {
        return _delegate.get( arg0 );
    }

    @Override
    public IImmutableSortedMapView<K, V> headMap( final K toKey ) {
        return MapViews.asIImmutableSortedMapView( _delegate.headMap( toKey ) );
    }

    @Override
    public boolean isEmpty() {
        return _delegate.isEmpty();
    }

    @Override
    public IImmutableSetView<K> keySet() {
        return SetViews.asIImmutableSetView( _delegate.keySet() );
    }

    @Override
    public K lastKey() {
        return _delegate.lastKey();
    }

    @Override
    public int size() {
        return _delegate.size();
    }

    @Override
    public IImmutableSortedMapView<K, V> subMap( final K fromKey, final K toKey ) {
        return MapViews.asIImmutableSortedMapView( _delegate.subMap( fromKey, toKey ) );
    }

    @Override
    public IImmutableSortedMapView<K, V> tailMap( final K fromKey ) {
        return MapViews.asIImmutableSortedMapView( _delegate.tailMap( fromKey ) );
    }

    @Override
    public String toString() {
        return _delegate.toString();
    }

    @Override
    public IImmutableCollectionView<V> values() {
        return CollectionViews.asIImmutableCollectionView( _delegate.values() );
    }

    @Override
    public ImmutableSortedMapView<K, V> asMapView() {
        return MapViews.asImmutableSortedMapView( _delegate );
    }
    
    @Override
    public IMutableSortedMap<K, V> mutableCopy() {
        return MutableTreeMap.copyOf( this );
    }
    
    @Override
    public boolean isNotEmpty() {
        return !isEmpty();
    }
    
    @Override
    public boolean hasSingleEntryOnly() {
        return size() == 1;
    }

    @Override
    public K getSingleEntryKey() {
        return Iterables.getOnlyElement( keySet() );
    }

    @Override
    public V getSingleEntryValue() {
        return Iterables.getOnlyElement( values() );
    }
    
}
