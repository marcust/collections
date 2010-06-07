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
import java.util.Map;
import java.util.Map.Entry;

import org.thiesen.collections.collection.impl.CollectionViews;
import org.thiesen.collections.collection.views.IImmutableCollectionView;
import org.thiesen.collections.common.view.map.ImmutableMapView;
import org.thiesen.collections.map.IImmutableMap;
import org.thiesen.collections.map.IMap;
import org.thiesen.collections.map.IMutableMap;
import org.thiesen.collections.set.impl.SetViews;
import org.thiesen.collections.set.views.IImmutableSetView;

public final class ImmutableMap<K,V> implements IImmutableMap<K, V>, Serializable {

    private static final long serialVersionUID = -4095027582339406428L;
    
    private final com.google.common.collect.ImmutableMap<K, V> _delegate;

    private ImmutableMap( final com.google.common.collect.ImmutableMap<K, V> delegate ) {
        _delegate = delegate;
    }

    @SuppressWarnings( "unchecked" )
    public static <K,V> ImmutableMap<K,V> copyOf( final IMap<? extends K, ? extends V> map ) {
        if ( map instanceof ImmutableMap<?,?> ) {
            return (ImmutableMap<K, V>) map;
        }
        
        return new ImmutableMap<K,V>( com.google.common.collect.ImmutableMap.<K, V>copyOf( map.asMapView() ) );
    }

    public static <K,V> ImmutableMap<K,V> copyOf( final Map<? extends K, ? extends V> map ) {
        return new ImmutableMap<K,V>( com.google.common.collect.ImmutableMap.<K, V>copyOf( map ) );
    }

    public static <K,V> ImmutableMap<K,V> of() {
        return new ImmutableMap<K,V>( com.google.common.collect.ImmutableMap.<K, V>of() );
    }
    
    public static <K,V> ImmutableMap<K,V> of( final K k1, final V v1 ) {
        return new ImmutableMap<K,V>( com.google.common.collect.ImmutableMap.<K, V>of( k1, v1 ) );
    }
    
    public static <K,V> ImmutableMap<K,V> of( final K k1, final V v1, final K k2, final V v2 ) {
        return new ImmutableMap<K,V>( com.google.common.collect.ImmutableMap.<K, V>of( k1, v1, k2, v2 ) );
    }
    
    public static <K,V> ImmutableMap<K,V> of( final K k1, final V v1, final K k2, final V v2, final K k3, final V v3 ) {
        return new ImmutableMap<K,V>( com.google.common.collect.ImmutableMap.<K, V>of( k1, v1, k2, v2, k3, v3 ) );
    }
    
    public static <K,V> ImmutableMap<K,V> of( final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4 ) {
        return new ImmutableMap<K,V>( com.google.common.collect.ImmutableMap.<K, V>of( k1, v1, k2, v2, k3, v3, k4, v4 ) );
    }

    public static <K,V> ImmutableMap<K,V> of( final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5, final V v5 ) {
        return new ImmutableMap<K,V>( com.google.common.collect.ImmutableMap.<K, V>of( k1, v1, k2, v2, k3, v3, k4, v4, k5, v5 ) );
    }
    
    @Override
    public ImmutableMapView<K, V> asMapView() {
        return MapViews.asImmutableMapView( _delegate );
    }

    @Override
    public IImmutableSetView<Entry<K, V>> entrySet() {
        return SetViews.asIImmutableSetView( _delegate.entrySet() );
    }

    @Override
    public IImmutableSetView<K> keySet() {
        return SetViews.asIImmutableSetView( _delegate.keySet() );
        
    }

    @Override
    public IImmutableCollectionView<V> values() {
        return CollectionViews.asIImmutableCollectionView( _delegate.values() );
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
    public V get( final Object key ) {
        return _delegate.get( key );
    }

    @Override
    public boolean isEmpty() {
        return _delegate.isEmpty();
    }

    @Override
    public int size() {
        return _delegate.size();
    }

    @Override
    public IMutableMap<K, V> mutableCopy() {
        return MutableHashMap.copyOf( this );
    }

    @Override
    public boolean isNotEmpty() {
        return !isEmpty();
    }
    
}
