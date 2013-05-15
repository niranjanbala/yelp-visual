/*
 * File: app/view/MyViewport.js
 *
 * This file was generated by Sencha Architect version 2.1.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.1.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.1.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('MyApp.view.MyViewport', {
    extend: 'Ext.container.Viewport',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'tabpanel',
                    activeTab: 0,
                    items: [
                        {
                            xtype: 'panel',
                            height: 464,
                            title: 'Business',
                            items: [
                                {
                                    xtype: 'gridpanel',
                                    height: 420,
                                    store: 'BusinessStore',
                                    viewConfig: {

                                    },
                                    columns: [
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'business_id',
                                            text: 'Business_id'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'full_address',
                                            text: 'Full_address'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'open',
                                            text: 'Open'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'categories',
                                            text: 'Categories'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'city',
                                            text: 'City'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'review_count',
                                            text: 'Review_count'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'name',
                                            text: 'Name'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'neighborhoods',
                                            text: 'Neighborhoods'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'longitude',
                                            text: 'Longitude'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'state',
                                            text: 'State'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'stars',
                                            text: 'Stars'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'latitude',
                                            text: 'Latitude'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            dataIndex: 'type',
                                            text: 'Type'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'button',
                                    width: 554,
                                    text: 'Save'
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            height: 462,
                            title: 'Users'
                        },
                        {
                            xtype: 'panel',
                            height: 474,
                            title: 'Checkins'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});