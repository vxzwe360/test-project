package za.co.test.bsvc.restCommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import za.co.test.getallstudents.rest.GetAllStudentsService;
import za.co.test.searchstudent.rest.SearchStudentRequestDTO;
import za.co.test.searchstudent.rest.SearchStudentResponseDTO;
import za.co.test.searchstudent.rest.SearchStudentService;
import za.co.test.updatestudent.rest.UpdateStudentRequestDTO;
import za.co.test.updatestudent.rest.UpdateStudentResponseDTO;
import za.co.test.updatestudent.rest.UpdateStudentService;
import za.co.test.updatestudentscore.rest.UpdateStudentScoreRequestDTO;
import za.co.test.updatestudentscore.rest.UpdateStudentScoreResponseDTO;
import za.co.test.updatestudentscore.rest.UpdateStudentScoreService;
import za.co.test.createstudent.rest.CreateStudentRequestDTO;
import za.co.test.createstudent.rest.CreateStudentResponseDTO;
import za.co.test.createstudent.rest.CreateStudentService;
import za.co.test.deletestudent.rest.DeleteStudentRequestDTO;
import za.co.test.deletestudent.rest.DeleteStudentResponseDTO;
import za.co.test.deletestudent.rest.DeleteStudentService;
import za.co.test.getallstudents.rest.GetAllStudentsResponseDTO;

@RestController
public class RestServiceController {

	
	@Autowired
	private GetAllStudentsService getAllStudentsService;
	@Autowired
	private CreateStudentService createStudentService;
	@Autowired
	private SearchStudentService searchStudentService;
	@Autowired
	private DeleteStudentService deleteStudentService;
	@Autowired
	private UpdateStudentService updateStudentService;
	@Autowired
	private UpdateStudentScoreService updateStudentScoreService;
	
	@Operation(summary = "Get All Students")
	@GetMapping("/getallstudents")
	public GetAllStudentsResponseDTO searchcustomerGet() throws Exception {

		return getAllStudentsService.processRequest();
	}
	
	
	@Operation(summary = "create Student")
	@PostMapping("/createstudent")
	@RequestMapping(value = "/createstudent", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
	@Content(mediaType = "application/json", schema = @Schema(implementation = CreateStudentResponseDTO.class)) }) })
	public CreateStudentResponseDTO callCreateStudentService(@RequestBody CreateStudentRequestDTO requestStr) throws Exception {
		return createStudentService.createStudent(requestStr);
	}
	
	
	@Operation(summary = "Search Student")
	@PostMapping("/searchstudent")
	@RequestMapping(value = "/searchstudent", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
	@Content(mediaType = "application/json", schema = @Schema(implementation = SearchStudentResponseDTO.class)) }) })
	public SearchStudentResponseDTO callSearchStudentService(@RequestBody SearchStudentRequestDTO requestStr) throws Exception {
		return searchStudentService.searchStudent(requestStr);
	}
	
	
	@Operation(summary = "Delete Student")
	@PostMapping("/deletestudent")
	@RequestMapping(value = "/deletestudent", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
	@Content(mediaType = "application/json", schema = @Schema(implementation = DeleteStudentResponseDTO.class)) }) })
	public DeleteStudentResponseDTO callDeleteStudentService(@RequestBody DeleteStudentRequestDTO requestStr) throws Exception {
		return deleteStudentService.deleteStudent(requestStr);
	}
	
	
	@Operation(summary = "Update Student")
	@PostMapping("/updatestudent")
	@RequestMapping(value = "/updatestudent", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
	@Content(mediaType = "application/json", schema = @Schema(implementation = UpdateStudentResponseDTO.class)) }) })
	public UpdateStudentResponseDTO callUpdateStudentService(@RequestBody UpdateStudentRequestDTO requestStr) throws Exception {
		return updateStudentService.updateStudent(requestStr);
	}
	
	
	@Operation(summary = "Update Student SCore")
	@PostMapping("/updatestudentscore")
	@RequestMapping(value = "/updatestudentscore", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
	@Content(mediaType = "application/json", schema = @Schema(implementation = UpdateStudentScoreResponseDTO.class)) }) })
	public UpdateStudentScoreResponseDTO callUpdateStudentScoreService(@RequestBody UpdateStudentScoreRequestDTO requestStr) throws Exception {
		return updateStudentScoreService.updateStudent(requestStr);
	}
}
