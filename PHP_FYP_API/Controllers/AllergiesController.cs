using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class AllergiesController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();


        //  API to Get All Allergies     ->  C
        [HttpGet]
        public HttpResponseMessage Allergies()
        {
            try
            {
                var diseases = db.tb_Allergies.OrderBy(d => d.a_id).ToList();
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


        //  API to Add User Allergies    ->  C
        [HttpPost]
        public HttpResponseMessage AddingUserAllergies(tb_User_Allergies ua)
        {
            try
            {
                db.tb_User_Allergies.Add(ua);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, ua);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API to Get All User Allergies    ->  C
        [HttpGet]
        public HttpResponseMessage GetUserAllergies(int id)
        {
            try
            {
                var ua = db.tb_User_Allergies.Where(a => a.u_id == id).ToList();
                if (ua.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, ua);
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
