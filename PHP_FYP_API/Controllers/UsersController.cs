using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class UsersController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();


        //  API for User to Login       ->  C
        [HttpPost]
        public HttpResponseMessage LoginUser(tb_Users user)
        {
            try
            {
                var loginusers = db.tb_Users.FirstOrDefault(e => e.u_cnic == user.u_cnic
                                                                        &&
                                                            e.u_password == user.u_password);
                if (loginusers != null)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, loginusers);
                }
                else
                {
                    return Request.CreateResponse(HttpStatusCode.NotFound, "null");
                }
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API for User to Get All User       ->  C
        [HttpGet]
        public HttpResponseMessage AllUser()
        {
            try
            {
                var loginusers = db.tb_Users.OrderBy(e => e.u_id);
                if (loginusers != null)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, loginusers);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "null");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API for User to Get User       ->  C
        [HttpGet]
        public HttpResponseMessage GetUser(int id)
        {
            try
            {
                var user = db.tb_Users.Where(uds => uds.u_id == id).ToList();

                if (user.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.Found, user);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "null");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }



        //  API for User to Register    ->  C
        [HttpPost]
        public HttpResponseMessage RegisterUser(tb_Users user)
        {
            try
            {
                var result = db.tb_Users.FirstOrDefault(e => e.u_cnic == user.u_cnic);
                if (result != null)
                    return Request.CreateResponse(HttpStatusCode.Found, "User Already Register");
                else
                {
                    db.tb_Users.Add(user);
                    db.SaveChanges();
                    return Request.CreateResponse(HttpStatusCode.OK, user);
                }
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }
    }
}
