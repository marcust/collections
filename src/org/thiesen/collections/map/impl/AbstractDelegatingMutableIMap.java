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
import java.util.Map.Entry;

import org.thiesen.collections.collection.impl.CollectionViews;
import org.thiesen.collections.collection.views.IMutableCollectionView;
import org.thiesen.collections.common.view.map.MutableMapView;
import org.thiesen.collections.map.IMutableMap;
import org.thiesen.collections.map.views.IUnmodifiableMapView;
import org.thiesen.collections.set.impl.SetViews;
import org.thiesen.collections.set.views.IMutableSetView;

abstract class AbstractDelegatingMutableIMap<K,V>
    implements IMutableMap<K, V> {

    private final Map<K, V> _delegate;

    public AbstractDelegatingMutableIMap( final Map<K, V> delegate ) {
        _delegate = delegate;
    }

    @Override
    public void clear() {
        _delegate.clear();
    }

    @Override
    public IMutableSetView<K> keySet() {
        return SetViews.asIMutableSetView( _delegate.keySet() );
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
    public IMutableCollectionView<V> values() {
        return CollectionViews.asMutableCollectionView( _delegate.values() );
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
    public int size() {
        return _delegate.size();
    }

    @Override
    public MutableMapView<K, V> asMapView() {
        return MapViews.asMutableMapView( _delegate );
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

    
}
