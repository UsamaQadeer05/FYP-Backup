using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class DiseasesController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();

        //  API to Get All Diseases     ->  C
        [HttpGet]
        public HttpResponseMessage Diseases()
        {
            try
            {
                var diseases = db.tb_Diseases.OrderBy(d => d.d_id).ToList();
                if (diseases.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, diseases);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "No Record");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API to Add User Diseases    ->  C
        [HttpPost]
        public HttpResponseMessage AddingUserDiseases(tb_User_Diseases user_Disease)
        {
            try
            {
                //var result = db.tb_User_Disease.FirstOrDefault(ud => ud.u_email == user_Disease.u_email);
                //if (result != null)
                //    return Request.CreateResponse(HttpStatusCode.Found, "User Already Register");
                //else
                //{
                db.tb_User_Diseases.Add(user_Disease);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, user_Disease);
                //}
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API to Get All User Diseases    ->  C
        [HttpGet]
        public HttpResponseMessage GetUserDiseases(int id)
        {
            try
            {
                var user_Diseases = db.tb_User_Diseases.Where(ud => ud.u_id == id).ToList();
                if (user_Diseases.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, user_Diseases);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "No Record");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

    }
}
