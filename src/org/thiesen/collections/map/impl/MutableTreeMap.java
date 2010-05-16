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
import java.util.SortedMap;

import org.thiesen.collections.common.view.map.MutableSortedMapView;
import org.thiesen.collections.map.IImmutableSortedMap;
import org.thiesen.collections.map.IMutableSortedMap;
import org.thiesen.collections.map.ISortedMap;
import org.thiesen.collections.map.views.IMutableSortedMapView;

import com.google.common.collect.Maps;

public class MutableTreeMap<K, V>
    extends AbstractDelegatingMutableIMap<K,V>
    implements IMutableSortedMap<K, V>, Serializable {

    private static final long serialVersionUID = 8160226401221910796L;
    
    private final SortedMap<K, V> _delegate;

    private MutableTreeMap( final SortedMap<K,V> delegate ) {
        super( delegate );
        _delegate = delegate;
    }
    
    public static <K,V> MutableTreeMap<K,V> copyOf( final ISortedMap<K, ? extends V> entries ) {
        return new MutableTreeMap<K, V>( Maps.newTreeMap( entries.asMapView() ) );
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
    public K lastKey() {
        return _delegate.lastKey();
    }

    @Override
    public IMutableSortedMapView<K, V> headMap( final K toKey ) {
        return MapViews.asIMutableSortedMapView( _delegate.headMap( toKey ) );
    }

    @Override
    public IMutableSortedMapView<K, V> subMap( final K fromKey, final K toKey ) {
        return MapViews.asIMutableSortedMapView( _delegate.subMap( fromKey, toKey ) );
    }

    @Override
    public IMutableSortedMapView<K, V> tailMap( final K fromKey ) {
        return MapViews.asIMutableSortedMapView( _delegate.tailMap( fromKey ) );
    }

    @Override
    public MutableSortedMapView<K,V> asMapView() {
        return MapViews.asMutableSortedMapView( _delegate );
    }

    @Override
    public IImmutableSortedMap<K, V> immutableCopy() {
        return ImmutableSortedMap.copyOf( this );
    }


    
}
