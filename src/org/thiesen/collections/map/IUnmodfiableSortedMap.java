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
package org.thiesen.collections.map;

import java.util.Map;

import org.thiesen.collections.collection.views.IUnmodifiableCollectionView;
import org.thiesen.collections.common.view.map.UnmodifiableSortedMapView;
import org.thiesen.collections.map.views.IUnmodifiableSortedMapView;
import org.thiesen.collections.set.views.IUnmodifiableSetView;

public interface IUnmodfiableSortedMap<K, V> extends ISortedMap<K, V>, IUnmodifiableMap<K, V> {

    @Override
    IUnmodifiableSortedMapView<K,V> subMap(K fromKey, K toKey);
    
    @Override
    IUnmodifiableSortedMapView<K,V> headMap(K toKey);
    
    @Override
    IUnmodifiableSortedMapView<K,V> tailMap(K fromKey);

    @Override
    IUnmodifiableSetView<K> keySet();
    
    @Override
    IUnmodifiableCollectionView<V> values();
    
    @Override
    IUnmodifiableSetView<Map.Entry<K, V>> entrySet();
    
    @Override
    UnmodifiableSortedMapView<K,V> asMapView();
    


}
