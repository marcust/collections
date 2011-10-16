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

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import org.thiesen.collections.collection.impl.CollectionViews;
import org.thiesen.collections.collection.views.IImmutableCollectionView;
import org.thiesen.collections.collection.views.IMutableCollectionView;
import org.thiesen.collections.collection.views.IUnmodifiableCollectionView;
import org.thiesen.collections.common.view.map.ImmutableMapView;
import org.thiesen.collections.common.view.map.ImmutableSortedMapView;
import org.thiesen.collections.common.view.map.MutableMapView;
import org.thiesen.collections.common.view.map.MutableSortedMapView;
import org.thiesen.collections.common.view.map.UnmodifiableMapView;
import org.thiesen.collections.map.IImmutableSortedMap;
import org.thiesen.collections.map.IMutableMap;
import org.thiesen.collections.map.views.IImmutableSortedMapView;
import org.thiesen.collections.map.views.IMutableSortedMapView;
import org.thiesen.collections.map.views.IUnmodifiableMapView;
import org.thiesen.collections.set.impl.SetViews;
import org.thiesen.collections.set.views.IImmutableSetView;
import org.thiesen.collections.set.views.IMutableSetView;
import org.thiesen.collections.set.views.IUnmodifiableSetView;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingSortedMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public class MapViews {

    private static class UnmodifiableMapViewImpl<K,V> 
        extends ForwardingMap<K, V>
        implements UnmodifiableMapView<K, V> {

        private final Map<K, V> _delegate;

        public UnmodifiableMapViewImpl( final Map<K, V> map ) {
            _delegate = Collections.unmodifiableMap( map );
        }

        @Override
        protected Map<K, V> delegate() {
            return _delegate;
        }

    }

    private static class IUnmodifiableMapViewImpl<K, V> implements IUnmodifiableMapView<K, V> {

        private final Map<K, V> _map;

        public IUnmodifiableMapViewImpl( final Map<K, V> map ) {
            _map = Collections.unmodifiableMap( map );
        }

        @Override
        public UnmodifiableMapView<K, V> asMapView() {
            return new UnmodifiableMapViewImpl<K,V>( _map );
        }

        @Override
        public IUnmodifiableSetView<Entry<K, V>> entrySet() {
            return SetViews.asIUnmodifiableSetView( _map.entrySet() );
        }

        @Override
        public IUnmodifiableSetView<K> keySet() {
            return SetViews.asIUnmodifiableSetView( _map.keySet() );

        }

        @Override
        public IUnmodifiableCollectionView<V> values() {
            return CollectionViews.asIUnmodifiableCollectionView( _map.values() );
        }

        @Override
        public boolean containsKey( final Object key ) {
            return _map.containsKey( key );
        }

        @Override
        public boolean containsValue( final Object value ) {
            return _map.containsValue( value );
        }

        @Override
        public V get( final Object key ) {
            return _map.get( key );
        }

        @Override
        public boolean isEmpty() {
            return _map.isEmpty();
        }

        @Override
        public int size() {
            return _map.size();
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

    private static class MutableSortedMapViewImpl<K, V> 
        extends ForwardingSortedMap<K, V>
        implements MutableSortedMapView<K, V> {

        private final SortedMap<K, V> _delegate;

        public MutableSortedMapViewImpl( final SortedMap<K, V> delegate ) {
            _delegate = delegate;
        }

        @Override
        protected SortedMap<K, V> delegate() {
            return _delegate;
        }

    }

    private static class ImmutableSortedMapViewImpl<K, V>
        extends ForwardingSortedMap<K, V>
        implements ImmutableSortedMapView<K, V> {

        private final ImmutableSortedMap<K, V> _delegate;

        public ImmutableSortedMapViewImpl( final ImmutableSortedMap<K, V> delegate ) {
            _delegate = delegate;
        }

        @Override
        protected SortedMap<K, V> delegate() {
            return _delegate;
        }

    }

    private static class IImmutableSortedMapViewImpl<K, V> 
        implements IImmutableSortedMapView<K, V> {

        public ImmutableSortedMap<K, V> _map;

        public IImmutableSortedMapViewImpl( final ImmutableSortedMap<K, V> map ) {
            _map = map;
        }

        @Override
        public ImmutableSortedMapView<K, V> asMapView() {
            return new ImmutableSortedMapViewImpl<K, V>( _map );
        }

        @Override
        public boolean containsKey( final Object key ) {
            return _map.containsKey( key );
        }

        @Override
        public boolean containsValue( final Object value ) {
            return _map.containsValue( value );
            
        }

        @Override
        public IImmutableSetView<Entry<K, V>> entrySet() {
            return SetViews.asIImmutableSetView( _map.entrySet() );
        }

        @Override
        public V get( final Object key ) {
            return _map.get( key );
        }

        @Override
        public boolean isEmpty() {
            return _map.isEmpty();
        }

        @Override
        public IImmutableSetView<K> keySet() {
            return SetViews.asIImmutableSetView( _map.keySet() );
        }

        @Override
        public int size() {
            return _map.size();
        }

        @Override
        public IImmutableCollectionView<V> values() {
            return CollectionViews.asIImmutableCollectionView( _map.values() );
        }

        @Override
        public Comparator<? super K> comparator() {
            return _map.comparator();
        }

        @Override
        public K firstKey() {
            return _map.firstKey();
        }

        @Override
        public IImmutableSortedMapView<K, V> headMap( final K toKey ) {
            return new IImmutableSortedMapViewImpl<K, V>( _map.headMap( toKey ) ); 
        }

        @Override
        public K lastKey() {
            return _map.lastKey();
        }

        @Override
        public IImmutableSortedMapView<K, V> subMap( final K fromKey, final K toKey ) {
            return new IImmutableSortedMapViewImpl<K, V>( _map.subMap( fromKey, toKey ) );
        }

        @Override
        public IImmutableSortedMapView<K, V> tailMap( final K fromKey ) {
            return new IImmutableSortedMapViewImpl<K, V>( _map.tailMap( fromKey ) );
        }

        @Override
        public IMutableMap<K, V> mutableCopy() {
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

        @Override
        public IImmutableSortedMap<K, V> append( final K key, final V value ) {
            final Map<K,V> newMap = Maps.newHashMap( asMapView() );
            newMap.put( key, value );
            return org.thiesen.collections.map.impl.ImmutableSortedMap.copyOf( newMap ); 
        }


    }

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

    private static class IMutableSortedMapViewImpl<K, V> 
        implements IMutableSortedMapView<K, V> {

        private final SortedMap<K, V> _delegate;

        public IMutableSortedMapViewImpl( final SortedMap<K, V> map ) {
            _delegate = map;
        }

        @Override
        public MutableSortedMapView<K, V> asMapView() {
            return new MutableSortedMapViewImpl<K, V>( _delegate );
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

        @Override
        public Comparator<? super K> comparator() {
            return _delegate.comparator();
        }

        @Override
        public K firstKey() {
            return _delegate.firstKey();
        }

        @Override
        public IMutableSortedMapView<K, V> headMap( final K toKey ) {
            return new IMutableSortedMapViewImpl<K,V>( _delegate.headMap( toKey ) );
        }

        @Override
        public K lastKey() {
            return _delegate.lastKey(); 
            
        }

        @Override
        public IMutableSortedMapView<K, V> subMap( final K fromKey, final K toKey ) {
            return new IMutableSortedMapViewImpl<K,V>( _delegate.subMap( fromKey, toKey ) );
        }

        @Override
        public IMutableSortedMapView<K, V> tailMap( final K fromKey ) {
            return new IMutableSortedMapViewImpl<K, V>( _delegate.tailMap( fromKey ) );
            
        }

        @Override
        public IImmutableSortedMap<K, V> immutableCopy() {
            return org.thiesen.collections.map.impl.ImmutableSortedMap.copyOf( this );
        }

        @Override
        public IUnmodifiableMapView<K, V> asUnmodifiableView() {
            return MapViews.asIUnmodifiableMapView( _delegate );
            
        }

        @Override
        public boolean putIfNonNull( final K key, final V value ) {
            if ( value == null ) {
                return false;
            }
            
            put( key, value );
            return true;
        }
        
        @Override
        public boolean isNotEmpty() {
            return !isEmpty();
        }

        @Override
        public IMutableSortedMapViewImpl<K, V> append( final K key, final V value ) {
            put( key, value );
            return this;
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
    
    public static <K,V> IImmutableSortedMapView<K, V> asIImmutableSortedMapView( final ImmutableSortedMap<K, V> map ) {
        return new IImmutableSortedMapViewImpl<K, V>( map ); 
    }
    
    public static <K,V> ImmutableSortedMapView<K,V> asImmutableSortedMapView( final ImmutableSortedMap<K,V> map ) {
        return new ImmutableSortedMapViewImpl<K,V>( map );
    }
    
    public static <K,V> IUnmodifiableMapView<K, V> asIUnmodifiableMapView( final Map<K,V> map ) {
        return new IUnmodifiableMapViewImpl<K,V>( map );
    }
    
}
