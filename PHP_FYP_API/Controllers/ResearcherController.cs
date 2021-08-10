using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class ResearcherController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();


        //  API to Get Country Wise Diseases     ->  C
        [HttpGet]
        public HttpResponseMessage GetCountriesDiseases()
        {
            try
            {
                var c = db.Database.SqlQuery<COUNTRYPIECHARTS_Result>
                    ("Execute COUNTRYPIECHARTS").ToList<COUNTRYPIECHARTS_Result>();
                return Request.CreateResponse(HttpStatusCode.OK, c);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API to Get City Wise Diseases     ->  C
        [HttpGet]
        public HttpResponseMessage GetCitiesDiseases(string CITYNAME)
        {
            try
            {
                var c = db.Database.SqlQuery<CITYPIECHARTSS_Result>
                    ("Execute CITYPIECHARTSS @CITYNAME=" + "'" + CITYNAME + "'").ToList<CITYPIECHARTSS_Result>();
                return Request.CreateResponse(HttpStatusCode.OK, c);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        //  API to Get City Wise Diseases     ->  C
        [HttpPost]
        public HttpResponseMessage GetCitiesDisease(CITYPIECHARTSS_Result CITYNAME)
        {
            try
            {
                var c = db.Database.SqlQuery<CITYPIECHARTSS_Result>
                    ("Execute CITYPIECHARTSS @CITYNAME=" + "'" + CITYNAME.d_name + "'").ToList<CITYPIECHARTSS_Result>();
                return Request.CreateResponse(HttpStatusCode.OK, c);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }
    }
}
