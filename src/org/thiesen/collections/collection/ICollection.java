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
package org.thiesen.collections.collection;

import org.thiesen.collections.common.view.collection.CollectionView;


public interface ICollection<E> extends Iterable<E>  {

    public boolean contains(Object o);
    
    public boolean containsAll(Iterable<?> c);
  
    public boolean isEmpty();
    
    public boolean isNotEmpty();
    
    int size();
    
    public Object[] toArray();
    
    <T> T[] toArray(T[] a);
    
    public java.util.Collection<E> copyToMutableCollections();
    
    public CollectionView<E> asCollectionsView();
    
    public boolean hasSingleValueOnly();
    
    public E getSingleValue();
    
    
}
