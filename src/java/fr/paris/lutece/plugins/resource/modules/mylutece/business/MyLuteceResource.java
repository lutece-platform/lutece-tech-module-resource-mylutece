/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.resource.modules.mylutece.business;

import fr.paris.lutece.plugins.resource.business.IResource;
import fr.paris.lutece.portal.service.security.LuteceUser;


/**
 * Class that implement both IResource and IExtendableResource interfaces
 */
public class MyLuteceResource implements IResource
{
    /**
     * MyLutece user resource type
     */
    public static final String LUTECE_USER_RESOURCE_TYPE = "luteceuser";

    private static final long serialVersionUID = 5868561258462985889L;

    private String _strIdResource;
    private String _strName;

    /**
     * Creates a new MyLuteceResource from a lutece user
     * @param user the user to read data from
     */
    public MyLuteceResource( LuteceUser user )
    {
        this._strIdResource = user.getName( );
        this._strName = user.getEmail( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdResource( )
    {
        return _strIdResource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResourceType( )
    {
        return LUTECE_USER_RESOURCE_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResourceName( )
    {
        return _strName;
    }
}
