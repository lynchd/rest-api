package ie.dit.users;

import ie.dit.users.exception.InvalidRequestExceptionMapper;
import ie.dit.users.exception.UserExistsExceptionMapper;
import ie.dit.users.exception.UserNotAuthorizedExceptionMapper;
import ie.dit.users.exception.UserNotFoundExceptionMapper;
import ie.dit.users.resources.LoginResource;
import ie.dit.users.resources.UserResource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class UsersApplication extends Application
{
   HashSet<Object> singletons = new HashSet<Object>();

   public UsersApplication()
   {
      singletons.add(new UserResource());
      singletons.add(new LoginResource());
      singletons.add(new InvalidRequestExceptionMapper());
      singletons.add(new UserExistsExceptionMapper());
      singletons.add(new UserNotAuthorizedExceptionMapper());
      singletons.add(new UserNotFoundExceptionMapper());
   }

   @Override
   public Set<Class<?>> getClasses()
   {
      HashSet<Class<?>> set = new HashSet<Class<?>>();
      return set;
   }

   @Override
   public Set<Object> getSingletons()
   {
      return singletons;  
   }
}