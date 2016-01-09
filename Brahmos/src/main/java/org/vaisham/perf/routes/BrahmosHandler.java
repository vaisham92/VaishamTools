package org.vaisham.perf.routes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaisham.perf.autogen.Builds;
import org.vaisham.perf.autogen.Consolidatedreport;
import org.vaisham.perf.autogen.Projects;
import org.vaisham.perf.autogen.Scenarios;
import org.vaisham.perf.core.IJmeterCSVReader;
import org.vaisham.perf.core.JmeterCSVReader2;
import org.vaisham.perf.dto.BuildsDTO;
import org.vaisham.perf.dto.ConsolidatedReportsDTO;
import org.vaisham.perf.dto.ConsolidatedReportsMetaDTO;
import org.vaisham.perf.manage.exceptions.ProjectNotFoundException;
import org.vaisham.perf.manage.exceptions.ScenarioNotFoundException;
import org.vaisham.perf.serviceinterface.IBuildsService;
import org.vaisham.perf.serviceinterface.IConsolidateReportService;
import org.vaisham.perf.serviceinterface.IProjectsService;
import org.vaisham.perf.serviceinterface.IScenariosService;
import org.vaisham.perf.utils.GsonHelper;

import com.sun.jersey.multipart.FormDataParam;

@Path("")
@Component
public class BrahmosHandler {

	private static GsonHelper gsonHelper = new GsonHelper();
	private static IJmeterCSVReader jmeterCSVReader = new JmeterCSVReader2();

	@Autowired
	public IProjectsService iProjectsSerice;

	@Autowired
	public IScenariosService iScenariosService;

	@Autowired
	public IBuildsService iBuildsService;

	@Autowired
	public IConsolidateReportService iConsolidatedReportService;

	@GET
	@Path("/test/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Brahmos says : " + msg;
		return Response.status(200).entity(output).build();
	}

	/** Projects function **/
	@GET
	@Path("/projects")
	@Produces("application/json")
	public Response getAllProjects() {
		List<Projects> projects = iProjectsSerice.getAllProjects();
		String output = gsonHelper.convertToJson(projects);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/projects/new")
	@Produces("application/json")
	public Response createNewProject(@FormParam("name") String name,
			@FormParam("description") String description,
			@FormParam("comments") String comments) {
		Projects projects = iProjectsSerice.createProject(name, description,
				comments);
		String output = gsonHelper.convertToJson(projects);
		return Response.status(201).entity(output).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/projects/{projectId}/update")
	@Produces("application/json")
	public Response updateProject(@PathParam("projectId") int projectId,
			@FormParam("name") String name,
			@FormParam("description") String description,
			@FormParam("comments") String comments) {
		Projects projects = iProjectsSerice.updateProject(projectId, name,
				description, comments);
		String output = gsonHelper.convertToJson(projects);
		return Response.status(200).entity(output).build();
	}

	@DELETE
	@Path("/projects/{projectId}")
	public Response deleteProject(
			@PathParam("projectId") int projectId) {
		try {
			iProjectsSerice.deleteProject(projectId);
			return Response.status(200).entity("").build();
		} catch (HibernateException e) {
			return Response.status(500).entity("").build();
		} catch (ProjectNotFoundException e) {
			return Response.status(400).entity("").build();
		}
	}

	@GET
	@Path("/projects/{projectId}")
	@Produces("application/json")
	public Response getProjects(@PathParam("projectId") int projectId) {
		Projects projects;
		projects = iProjectsSerice.getProject(projectId);
		String output = gsonHelper.convertToJson(projects);
		return Response.status(200).entity(output).build();
	}

	/** End project functions **/

	/** Start scenarios functions **/

	@GET
	@Path("projects/{projectId}/scenarios")
	@Produces("application/json")
	public Response getScenariosByProject(@PathParam("projectId") int projectId) {
		List<Scenarios> scenarios = iScenariosService
				.getScenariosByProject(projectId);
		String output = gsonHelper.convertToJson(scenarios);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("projects/{projectId}/scenarios/new")
	@Produces("application/json")
	public Response createNewScenario(@PathParam("projectId") int projectId,
			@FormParam("name") String name,
			@FormParam("description") String description,
			@FormParam("defaultBuildInfo") String defaultBuildInfo,
			@FormParam("comments") String comments) {
		Scenarios scenario = iScenariosService.createScenario(projectId, name,
				description, defaultBuildInfo, comments);
		String output = gsonHelper.convertToJson(scenario);
		return Response.status(201).entity(output).build();
	}

	@DELETE
	@Path("/projects/{projectId}/scenarios/{scenarioId}")
	public Response deleteScenario(
			@PathParam("projectId") int projectId,
			@PathParam("scenarioId") int scenarioId) {
		try {
			iScenariosService.deleteScenario(projectId, scenarioId);
			return Response.status(200).entity("").build();
		} catch (HibernateException e) {
			return Response.status(500).entity("").build();
		} catch (ScenarioNotFoundException e) {
			return Response.status(400).entity("").build();
		}
	}

	@GET
	@Path("projects/{projectId}/scenarios/{scenarioId}")
	@Produces("application/json")
	public Response getScenariosByProject(
			@PathParam("projectId") int projectId,
			@PathParam("scenarioId") int scenarioId) {
		Scenarios scenarios = iScenariosService.getScenario(scenarioId);
		String output = gsonHelper.convertToJson(scenarios);
		return Response.status(200).entity(output).build();
	}

	/** End scenarios functions **/

	/** Start consolidated report functions **/
	@GET
	@Path("/consolidatedReport/meta")
	@Produces("application/json")
	public Response getConsolidatedResultsMeta() {
		List<ConsolidatedReportsMetaDTO> consolidateReportsData = iConsolidatedReportService
				.getConsolidatedMeta();
		String output = gsonHelper.convertToJson(consolidateReportsData);
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/consolidatedReport")
	@Produces("application/json")
	public Response getConsolidatedResultsData(
			@QueryParam("year") int year, 
			@QueryParam("month") String month) {
		List<ConsolidatedReportsDTO> consolidateReportsData = iConsolidatedReportService
				.getConsolidatedReportsWithNames(year, month);
		String output = gsonHelper.convertToJson(consolidateReportsData);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/consolidatedReport")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public Response postConsolidatedResultsData(
			@FormParam("projectId") int projectId,
			@FormParam("scenarioId") int scenarioId,
			@FormParam("averageResponseTime") String averageResponseTime,
			@FormParam("maximumThroughput") String maximumThroughput,
			@FormParam("transactionCount") String transactionCount,
			@FormParam("cpuUtilization") String cpuUtilization,
			@FormParam("comments") String comments) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int monthIndex = Calendar.getInstance().get(Calendar.MONTH);
		String month = getMonthByIndex(monthIndex);
		Consolidatedreport consolidatedReport = iConsolidatedReportService.updateConsolidatedReport(projectId, scenarioId, year, month, averageResponseTime, maximumThroughput, transactionCount, cpuUtilization);
		String output = gsonHelper.convertToJson(consolidatedReport);
		return Response.status(200).entity(output).build();
	}
	/** End consolidated report functions **/

	/** Start builds functions **/
	@GET
	@Path("projects/{projectId}/scenarios/{scenarioId}/builds")
	@Produces("application/json")
	public Response getBuildsForScenario(@PathParam("scenarioId") int scenarioId) {
		List<BuildsDTO> buildsDTO = iBuildsService.getBuildsByScenarioId(scenarioId);
		String output = gsonHelper.convertToJson(buildsDTO);
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("projects/{projectId}/scenarios/{scenarioId}/builds/{buildNumber}")
	@Produces("application/json")
	public Response getBuildForScenarioAndBuild(
			@PathParam("scenarioId") int scenarioId,
			@PathParam("buildNumber") int id) {
		Builds build = iBuildsService.getBuild(id, scenarioId);
		//System.out.println(gsonHelper.convertToJson(build));
		return Response.status(200).entity(gsonHelper.convertToJson(build)).build();
	}

	@POST
	@Path("projects/{projectId}/scenarios/{scenarioId}/builds/new")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public Response addNewBuild(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("id") int id,
			@PathParam("projectId") int projectId,
			@PathParam("scenarioId") int scenarioId,
			@FormDataParam("description") String description,
			@FormDataParam("comments") String comments) {
		String filePath = "D://1.csv";
		saveFile(fileInputStream, filePath);
		try {
			Builds build = iBuildsService.createBuild(id, scenarioId,
					description, jmeterCSVReader.extractBuildDataFromFile(filePath).getBytes(), comments);
			BuildsDTO buildsDTO = new BuildsDTO(build);
			String output = gsonHelper.convertToJson(buildsDTO);
			return Response.status(200).entity(output).build();
		} catch (FileNotFoundException e) {
			return Response.status(500).entity("error").build();
		}
	}

	/** End builds functions **/

	/** End Result Analyzer functions **/
	@POST
	@Path("/resultAnalyzer")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public Response getDataForResultAnalyzer(
			@FormDataParam("file") InputStream fileInputStream) {
		String filePath = System.getProperty("user.dir");
		saveFile(fileInputStream, filePath);
		try {
			return Response.status(200).entity(jmeterCSVReader.extractBuildDataFromFile(filePath)).build();
		} catch (FileNotFoundException e) {
			return Response.status(500).entity("error").build();
		}
	}
	/** End Result Analyzer functions **/

	private String getMonthByIndex(int index) {
		switch(index) {
		case 0:
			return "January";
		case 1:
			return "February";
		case 2:
			return "March";
		case 3:
			return "April";
		case 4:
			return "May";
		case 5:
			return "June";
		case 6:
			return "July";
		case 7:
			return "August";
		case 8:
			return "September";
		case 9:
			return "October";
		case 10:
			return "November";
		case 11:
			return "December";
		default: 
				return null;
		}
	}
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {
		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean createDirectoryInFilepath(String filepath, String directoryName) {
		File file = new File(filepath + "\\" + directoryName);
		if (!file.exists()) {
			if (file.mkdir()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	private boolean doesDirectoryExists(String filepath) {
		File file = new File(filepath);
		return file.exists();
	}
	
	private String getFilePathForBuilds(int projectId, int scenarioId, int buildNumber) {
		return projectId + "\"" + scenarioId + "\"" + buildNumber;
	}
}