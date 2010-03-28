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

import org.thiesen.collections.common.view.map.MutableMapView;

import com.google.common.collect.ForwardingMap;

public class MapViews {

    
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
    
}
