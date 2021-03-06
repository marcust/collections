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

import org.thiesen.collections.collection.views.IMutableCollectionView;
import org.thiesen.collections.common.view.map.MutableSortedMapView;
import org.thiesen.collections.map.views.IMutableSortedMapView;
import org.thiesen.collections.set.views.IMutableSetView;

public interface IMutableSortedMap<K, V> extends IMutableMap<K, V>, ISortedMap<K, V> {

    @Override
    IMutableSortedMapView<K,V> subMap(K fromKey, K toKey);
    
    @Override
    IMutableSortedMapView<K,V> headMap(K toKey);
    
    @Override
    IMutableSortedMapView<K,V> tailMap(K fromKey);

    @Override
    IMutableSetView<K> keySet();
    
    @Override
    IMutableCollectionView<V> values();
    
    @Override
    IMutableSetView<Map.Entry<K, V>> entrySet();
    
    @Override
    MutableSortedMapView<K,V> asMapView();

    @Override
    IImmutableSortedMap<K,V> immutableCopy();
}
