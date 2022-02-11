/*
 * Copyright (c) 2002-2017, Mairie de Paris
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
package fr.paris.lutece.plugins.resource.modules.mylutece.service.provider;

import fr.paris.lutece.plugins.resource.business.IResource;
import fr.paris.lutece.plugins.resource.business.IResourceType;
import fr.paris.lutece.plugins.resource.business.ResourceTypeDefaultImplementation;
import fr.paris.lutece.plugins.resource.modules.mylutece.business.MyLuteceResource;
import fr.paris.lutece.plugins.resource.service.ResourceCacheService;
import fr.paris.lutece.plugins.resource.service.provider.IResourceProvider;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.LuteceUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Resource provider for mylutece users
 */
public class MyLuteceResourceProvider implements IResourceProvider
{
    private static final String MESSAGE_LUTECE_USER_DESCRIPTION = "module.resource.mylutece.luteceUserDescription";

    private List<IResourceType> _listResourceType;
    private IResourceType _resourceType;

    /**
     * Default constructor
     */
    public MyLuteceResourceProvider( )
    {
        _listResourceType = new ArrayList<>( 1 );
        _resourceType = new ResourceTypeDefaultImplementation( MyLuteceResource.LUTECE_USER_RESOURCE_TYPE,
                I18nService.getLocalizedString( MESSAGE_LUTECE_USER_DESCRIPTION, Locale.getDefault( ) ) );
        _listResourceType.add( _resourceType );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IResourceType> getResourceTypeList( )
    {
        return _listResourceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isResourceTypeManaged( String strResourceTypeName )
    {
        return MyLuteceResource.LUTECE_USER_RESOURCE_TYPE.equals( strResourceTypeName );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResource getResource( String strIdResource, String strResourceTypeName )
    {
        String strCacheKey = ResourceCacheService.getResourceCacheKey( strIdResource, strResourceTypeName );
        MyLuteceResource resource = (MyLuteceResource) ResourceCacheService.getInstance( ).getFromCache( strCacheKey );
        if ( resource == null )
        {
            LuteceUser user = LuteceUserService.getLuteceUserFromName( strIdResource );
            if ( user != null )
            {
                resource = new MyLuteceResource( user );
                ResourceCacheService.getInstance( ).putInCache( strCacheKey, resource );
            }
        }
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IResource> getListResources( String strResourceTypeName )
    {
        // it is not possible to get the list of mylutece users
        return new ArrayList<>( 0 );
    }
}
