/*
 * Copyright (C) 2015 Vinaya Prasad N
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nvinayshetty.DTOnator.NameConventionCommands;

import com.nvinayshetty.DTOnator.FeedValidator.KeywordClasifier;
import com.nvinayshetty.DTOnator.Utility.DtoHelper;

/**
 * Created by vinay on 12/7/15.
 */
public class NamePrefixer implements NameParserCommand {
    private String prefix;

    public NamePrefixer(String prefix) {
        this.prefix = prefix;
    }

    public static NamePrefixer prefixWith(String prefix) {
        return new NamePrefixer(prefix);
    }

    @Override
    public String parseFieldName(String name) {
        String nameINUpperCase = DtoHelper.firstetterToUpperCase(name);
        return prefix + nameINUpperCase;
    }

    @Override
    public String undoParsing(String name) {
        String unparsed = name;
        if (name.startsWith(prefix))
            unparsed = name.replaceFirst(prefix, "");

        if (!new KeywordClasifier().isValidJavaIdentifier(unparsed))
            unparsed = name;
        return unparsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        NamePrefixer that = (NamePrefixer) o;
        return o.getClass().getName().equals(that.getClass().getName());

    }

    @Override
    public int hashCode() {
        return this.getClass().getName().hashCode();
    }
}
