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

import java.util.Map.Entry;

import org.thiesen.collections.collection.impl.CollectionViews;
import org.thiesen.collections.collection.views.IImmutableCollectionView;
import org.thiesen.collections.common.view.map.ImmutableMapView;
import org.thiesen.collections.map.IImmutableMap;
import org.thiesen.collections.set.impl.SetViews;
import org.thiesen.collections.set.views.IImmutableSetView;

import com.google.common.collect.ImmutableMap;

public final class ImmutableMapImpl<K,V> implements IImmutableMap<K, V> {

    private final ImmutableMap<K, V> _delegate;

    private ImmutableMapImpl( final ImmutableMap<K, V> delegate ) {
        _delegate = delegate;
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
    
    
}
