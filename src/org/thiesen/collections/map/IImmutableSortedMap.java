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

import org.thiesen.collections.collection.views.IImmutableCollectionView;
import org.thiesen.collections.common.view.map.ImmutableSortedMapView;
import org.thiesen.collections.map.views.IImmutableSortedMapView;
import org.thiesen.collections.set.views.IImmutableSetView;

public interface IImmutableSortedMap<K,V> extends IImmutableMap<K, V>, ISortedMap<K, V> {


    IImmutableSortedMapView<K,V> subMap(K fromKey, K toKey);
    
    IImmutableSortedMapView<K,V> headMap(K toKey);
    
    IImmutableSortedMapView<K,V> tailMap(K fromKey);

    IImmutableSetView<K> keySet();
    
    IImmutableCollectionView<V> values();
    
    IImmutableSetView<Map.Entry<K, V>> entrySet();
    
    ImmutableSortedMapView<K,V> asMapView();
    
    IMutableSortedMap<K,V> mutableCopy();
}
