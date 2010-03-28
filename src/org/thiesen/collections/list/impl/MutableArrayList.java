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
package org.thiesen.collections.list.impl;

import java.util.ArrayList;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.list.IMutableRandomAccessList;

import com.google.common.collect.Lists;

public class MutableArrayList<E> extends AbstractDelegatingMutableIList<E>
    implements IMutableRandomAccessList<E> {
 
    private final ArrayList<E> _arrayList;

    private MutableArrayList( final ArrayList<E> arrayList ) {
        super( arrayList );
        _arrayList = arrayList;
    }

    public static <T> MutableArrayList<T> withInitialCapacity( final int capacity ) {
        return new MutableArrayList<T>( Lists.<T>newArrayListWithCapacity(  capacity ) );
    }
    
    public static <T> MutableArrayList<T> withExpectedSize( final int capacity ) {
        return new MutableArrayList<T>( Lists.<T>newArrayListWithExpectedSize( capacity ) );
    }
    
    public static <T> MutableArrayList<T> copyOf( final ICollection<? extends T> values ) {
        return new MutableArrayList<T>( Lists.newArrayList( values.asCollectionsView() ) );
    }

    public static <T> MutableArrayList<T> copyOf( final Iterable<T> values ) {
        return new MutableArrayList<T>( Lists.newArrayList( values ) );
    }
    
    public static <T> MutableArrayList<T> copyOf( final T... values ) {
        return new MutableArrayList<T>( Lists.newArrayList( values ) );
    }
    
    @SuppressWarnings( "unchecked" )
    @Override
    public java.util.List<E> copyToMutableCollections() {
        return (java.util.List<E>) _arrayList.clone();
    }    
}
