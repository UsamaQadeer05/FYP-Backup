using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class VitalSignsController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();



        // API to Get User Vitals   ->  C
        [HttpGet]
        public HttpResponseMessage GetUserVitals(int id)
        {
            try
            {
                var vital = db.tb_User_Vitals.Where(uv => uv.u_id == id).ToList();
                if (vital.Count() != 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, vital);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "No Record");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        // API to Add User Vitals   ->  C 
        [HttpPost]
        public HttpResponseMessage AddingUserVitals(tb_User_Vitals user_vitals)
        {
            try
            {
                db.tb_User_Vitals.Add(user_vitals);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, user_vitals);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

    }
}
