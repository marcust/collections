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

import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;

import org.thiesen.collections.collection.impl.CollectionViews;
import org.thiesen.collections.collection.views.IMutableCollectionView;
import org.thiesen.collections.common.view.map.ImmutableMapView;
import org.thiesen.collections.common.view.map.MutableMapView;
import org.thiesen.collections.common.view.map.MutableSortedMapView;
import org.thiesen.collections.map.views.IMutableSortedMapView;
import org.thiesen.collections.set.impl.SetViews;
import org.thiesen.collections.set.views.IMutableSetView;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingSortedMap;
import com.google.common.collect.ImmutableMap;

public class MapViews {

    
    private static class ImmutableMapViewImpl<K,V> 
        extends ForwardingMap<K, V>
        implements ImmutableMapView<K, V> {

        private final ImmutableMap<K, V> _delegate;

        public ImmutableMapViewImpl( final ImmutableMap<K, V> delegate ) {
            _delegate = delegate;
        }

        @Override
        protected Map<K, V> delegate() {
            return _delegate;
        }

    }

    private static class IMutableSortedMapViewImpl<K, V> implements IMutableSortedMapView<K, V> {

        private final SortedMap<K, V> _delegate;

        public IMutableSortedMapViewImpl( final SortedMap<K, V> map ) {
            _delegate = map;
        }

        @Override
        public MutableMapView<K, V> asMapView() {
            return new MutableMapViewImpl<K, V>( _delegate );
        }

        @Override
        public boolean containsKey( final Object key ) {
            return _delegate.containsKey( key );
        }

        @Override
        public boolean containsValue( final Object value ) {
            return _delegate.containsValue( value );
        }

        @Override
        public IMutableSetView<Entry<K, V>> entrySet() {
            return SetViews.asIMutableSetView( _delegate.entrySet() );
        }

        @Override
        public V get( final Object key ) {
            return _delegate.get( key );
        }

        @Override
        public boolean isEmpty() {
            return _delegate.isEmpty();
        }

        @Override
        public IMutableSetView<K> keySet() {
            return SetViews.asIMutableSetView( _delegate.keySet() );
        }

        @Override
        public int size() {
            return _delegate.size();

        }

        @Override
        public IMutableCollectionView<V> values() {
            return CollectionViews.asMutableCollectionView( _delegate.values() );
        }

        @Override
        public void clear() {
            _delegate.clear();
        }

        @Override
        public V put( final K key, final V value ) {
            return _delegate.put( key, value );
        }

        @Override
        public void putAll( final Map<? extends K, ? extends V> m ) {
            _delegate.putAll( m );
        }

        @Override
        public V remove( final Object key ) {
            return _delegate.remove( key );
        }

    }

    private static class SortedMapViewImpl<K,V>
        extends ForwardingSortedMap<K, V>
        implements MutableSortedMapView<K, V> {

        private final SortedMap<K, V> _delegate;

        public SortedMapViewImpl( final SortedMap<K, V> map ) {
            _delegate = map;
        }

        @Override
        protected SortedMap<K, V> delegate() {
            return _delegate;
        }

    }

    private static class MutableMapViewImpl<K,V> 
        extends ForwardingMap<K, V>
        implements MutableMapView<K, V> {

        private final Map<K, V> _delegate;

        private MutableMapViewImpl( final Map<K,V> delegate ) {
            _delegate = delegate;
        }

        @Override
        protected Map<K, V> delegate() {
            return _delegate;
        }
        
        
    }

    public static <K,V> MutableMapView<K,V> asMutableMapView( final Map<K,V> map ) {
        return new MutableMapViewImpl<K,V>( map );
    }
    
    public static <K,V> MutableSortedMapView<K,V> asMutableSortedMapView( final SortedMap<K,V> map ) {
        return new SortedMapViewImpl<K,V>( map );
    }
    
    public static <K,V> IMutableSortedMapView<K,V> asIMutableSortedMapView( final SortedMap<K,V> map ) {
        return new IMutableSortedMapViewImpl<K,V>( map );
    }
    
    public static <K,V> ImmutableMapView<K,V> asImmutableMapView( final ImmutableMap<K, V> map ) {
        return new ImmutableMapViewImpl<K,V>( map );
    }
    
    
}
