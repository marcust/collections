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

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

public class MutableLinkedList<E> extends AbstractDelegatingMutableIList<E> implements Serializable  {

    private static final long serialVersionUID = -2582283793811761616L;
    
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
    
    public static <T> MutableLinkedList<T> copyOf( final T... elements ) {
        return new MutableLinkedList<T>( Lists.newLinkedList( Arrays.asList( elements ) ) );
    }
    
    public static <T> MutableLinkedList<T> of( final T... elements ) {
        return new MutableLinkedList<T>( Lists.newLinkedList( Arrays.asList( elements ) ) );
    }
    
    @SuppressWarnings( "unchecked" )
    @Override
    public List<E> copyToMutableCollections() {
        return (List<E>) _linkedList.clone();
    }
    
    @Override
    public MutableLinkedList<E> append( final E element ) {
        add( element );
        return this;
    }



}
