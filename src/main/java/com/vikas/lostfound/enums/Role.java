package com.vikas.lostfound.enums;

import java.util.Set;

public enum Role {
	ADMIN(
		    Set.of(

		        Permissions.USER_READ,
		        Permissions.USER_READ_ALL,
		        Permissions.USER_UPDATE,
		        Permissions.USER_DELETE,

		        Permissions.ITEM_CREATE,
		        Permissions.ITEM_BULK_CREATE,
		        Permissions.ITEM_READ,
		        Permissions.ITEM_UPDATE,
		        Permissions.ITEM_DELETE,
		        Permissions.ITEM_SEARCH,
		        Permissions.ITEM_CLAIM,

		        Permissions.DASHBOARD_ACCESS
		    )
		),
	
   USER(
		    Set.of(
		        Permissions.ITEM_CREATE,
		        Permissions.ITEM_READ,
		        Permissions.ITEM_UPDATE,
		        Permissions.ITEM_SEARCH,
		        Permissions.ITEM_CLAIM,

		        Permissions.USER_READ,
		        Permissions.USER_UPDATE
		    )
		);
	
   private final	Set<Permissions> permissions ;

   private Role(Set<Permissions> permissions) {
	this.permissions = permissions;
}

   public Set<Permissions> getPermissions() {
	return permissions;
}
   
}