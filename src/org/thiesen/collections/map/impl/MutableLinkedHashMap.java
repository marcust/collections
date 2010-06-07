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

import org.thiesen.collections.map.IImmutableMap;
import org.thiesen.collections.map.IMap;

import com.google.common.collect.Maps;

public class MutableLinkedHashMap<K, V>
    extends AbstractDelegatingMutableIMap<K,V>
    implements Serializable {

    private static final long serialVersionUID = -6765415598599564931L;

    private MutableLinkedHashMap( final Map<K,V> delegate ) {
        super( delegate );
    }
    
    public static <K,V> MutableLinkedHashMap<K,V> copyOf( final IMap<? extends K, ? extends V> entries ) {
        return new MutableLinkedHashMap<K, V>( Maps.newLinkedHashMap( entries.asMapView() ) );
        
    }
    
    public static <K,V> MutableLinkedHashMap<K,V> create() {
        return new MutableLinkedHashMap<K, V>( Maps.<K, V>newLinkedHashMap() );
    }

    @Override
    public IImmutableMap<K, V> immutableCopy() {
        return ImmutableMap.copyOf( this );
    }

    @Override
    public MutableLinkedHashMap<K, V> append( final K key, final V value ) {
        put( key, value );
        return this;
    }

}
