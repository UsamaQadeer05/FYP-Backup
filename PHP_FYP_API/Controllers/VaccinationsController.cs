using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class VaccinationsController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();


        //  API to Get All Vaccinations     ->  C
        [HttpGet]
        public HttpResponseMessage Vaccinations()
        {
            try
            {
                var diseases = db.tb_Vaccination.OrderBy(d => d.v_id).ToList();
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


        //  API to Add User Vacination
        [HttpPost]
        public HttpResponseMessage AddingUserVaccinations(tb_User_Vaccinations uv)
        {
            try
            {
                db.tb_User_Vaccinations.Add(uv);
                db.SaveChanges();

                var uva = db.tb_User_Vaccinations.OrderBy(v => v.u_id == uv.u_id);
                return Request.CreateResponse(HttpStatusCode.OK, uva.ToList());
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API to Get User Vaccination
        [HttpGet]
        public HttpResponseMessage GetUserVaccinations(int id)
        {
            try
            {
                var uv = db.tb_User_Vaccinations.Where(v => v.u_id == id).ToList();
                if (uv.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, uv);
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
