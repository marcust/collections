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

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

public class MutableLinkedList<E> extends AbstractDelegatingMutableIList<E>  {

    private final LinkedList<E> _linkedList;

    private MutableLinkedList( final LinkedList<E> list ) {
        super( list );
        _linkedList = list;
    }
    
    public static <T> MutableLinkedList<T> create() {
        return new MutableLinkedList<T>( new LinkedList<T>() );
    }
    
    public static <T> MutableLinkedList<T> copyOf( final Iterable<T> elements ) {
        return new MutableLinkedList<T>( Lists.newLinkedList( elements ) );
    }
    
    @SuppressWarnings( "unchecked" )
    @Override
    public List<E> copyToMutableCollections() {
        return (List<E>) _linkedList.clone();

    }


}
