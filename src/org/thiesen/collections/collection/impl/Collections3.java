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
package org.thiesen.collections.collection.impl;

import java.util.Collection;
import java.util.Iterator;

/**
 * Collection helper class for working with iterables.
 */
public class Collections3 {

    /**
     * A contains all implementation.
     * 
     * Returns true when the collection contains all of the objects in the given iterable.
     * 
     * @see Collection#contains(Object)
     * @param collection the collection that is tested for containing the objects.
     * @param iterable the iterable that contains the items which are checked.
     * @return true if the collection contains all of the items in iterable, false otherwise.
     */
    public static boolean containsAll( final Collection<?> collection, final Iterable<?> iterable ) {
        final Iterator<?> iterator = iterable.iterator();
        while ( iterator.hasNext() ) {
            if (!collection.contains( iterator.next() ) ) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean removeAll( final Collection<?> collection, final Iterable<?> iterable ) {
        boolean changed = false;
        final Iterator<?> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            if (collection.contains(iterator.next())) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    public static boolean retainAll( final Collection<?> collection, final Iterable<?> iterable ) {
        boolean changed = false;
        final Iterator<?> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    
}
