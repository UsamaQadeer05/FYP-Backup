using FYP_PHR.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using FYP_PHR.Models;

namespace FYP_PHR.Controllers
{
    public class UsersController : ApiController
    {
        PHREntities db = new PHREntities();

        //[HttpGet]
        //public List<tb_Users> AllUsers()
        //{
        //    return db.tb_Users.OrderBy(e => e.u_name).ToList();
        //}

        [HttpGet]
        public HttpResponseMessage GetUser(int id)
        {
            try
            {
                var allusers = db.tb_Users.Where(e => e.u_id == id);
                return Request.CreateResponse(HttpStatusCode.OK, allusers);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        public HttpResponseMessage AllUsers()
        {
            try
            {
                var allusers = db.tb_Users.OrderBy(e => e.u_name);
                return Request.CreateResponse(HttpStatusCode.OK, allusers.ToList());
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        public HttpResponseMessage LoginUsers(string email, string pass)
        {
            try
            {
                var loginusers = db.tb_Users.Where(e => e.u_email == email && e.u_pass == pass);
                return Request.CreateResponse(HttpStatusCode.OK, loginusers);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        public HttpResponseMessage LoginUser(string email, string pass)
        {
            try
            {
                //var loginusers = db.tb_Users.Where(e => e.u_email == email && e.u_pass == pass);
                var loginusers = db.tb_Users.FirstOrDefault(e => e.u_email == email && e.u_pass == pass);

                return Request.CreateResponse(HttpStatusCode.OK, loginusers);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        public HttpResponseMessage LoginUser(tb_Users user)
        {
            try
            {
                //var loginusers = db.tb_Users.Where(e => e.u_email == email && e.u_pass == pass);
                var loginusers = db.tb_Users.FirstOrDefault(e => e.u_email == user.u_email && e.u_pass == user.u_pass);

                return Request.CreateResponse(HttpStatusCode.OK, loginusers);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        [HttpPost]
        public HttpResponseMessage RegisterUser(tb_Users user)
        {
            try
            {
                var result = db.tb_Users.FirstOrDefault(e => e.u_email == user.u_email);
                if (result != null)
                    return Request.CreateResponse(HttpStatusCode.Conflict, "User Already Register");

                db.tb_Users.Add(user);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, user);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }
    }
}
