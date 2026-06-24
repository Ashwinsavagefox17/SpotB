from django.urls import path
from . import views

urlpatterns = [

        path('',views.home),
        path('login',views.login),
        path('ownerreg',views.ownerreg),
        path('admin_home',views.admin_home),
        path('admin_manage_driver',views.admin_manage_driver),
        path('driver_update/<id>',views.driver_update),
        path('driver_delete/<id>',views.driver_delete),
        path('view_owner',views.view_owner),
        path('owner_approve/<ids>',views.owner_approve),
        path('owner_reject/<ids>',views.owner_reject),
        path('owner_delete/<id>',views.owner_delete),
        path('owner_home',views.owner_home),
        path('owner_manage_bus',views.owner_manage_bus),
        path('update_bus/<id>',views.update_bus),
        path('delete_bus/<id>',views.delete_bus),
        path('admin_mange_routes',views.admin_mange_routes),
        path('update_routes/<id>',views.update_routes),
        path('delete_routes/<id>',views.delete_routes),
        path('admin_manage_stops/<id>',views.admin_manage_stops),
        path('update_stops/<id>',views.update_stops),
        path('delete_stops/<id>',views.delete_stops),
        path('admin_view_bus/<id>',views.admin_view_bus),
        path('block/<id>',views.block),
        path('unblock/<id>',views.unblock),
        path('allocate_bus/<id>',views.allocate_bus),
        path('get_stops/<int:route_id>/', views.get_stops, name='get_stops'),
        path('delete_allocate/<id>',views.delete_allocate),
        path('owner_manage_trip/<id>',views.owner_manage_trip),
        path('delete_trip/<id>',views.delete_trip),
        path('admin_view_feedback',views.admin_view_feedback),
        path('admin_view_rating',views.admin_view_rating),
        path('track',views.track),




        # --------------------------Andriod-----------------------

        path('login_and',views.login_and),
        path('passenger_registration',views.passenger_registration),
        path('view_bus',views.view_bus),
        path('psend_complaints',views.psend_complaints),
        path('view_reply',views.view_reply),
        path('psend_feedback',views.psend_feedback),
        path('and_view_route_search',views.and_view_route_search),
        path('update_pass_location',views.update_pass_location),
        path('driver_update_status',views.driver_update_status),



































]
