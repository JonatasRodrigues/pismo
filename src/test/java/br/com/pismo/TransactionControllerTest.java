package br.com.pismo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pismo.dto.TransactionDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@ActiveProfiles("test")
public class TransactionControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testSaveTransactionComDadosInconsistentesAccountIdNull() throws Exception {
		TransactionDTO dto = new TransactionDTO();
		dto.setAmount(new BigDecimal(1));
		dto.setOperationTypeId(1L);
		testSaveTransactionComDadosInconsistente(dto);
	}

	@Test
	public void testSaveTransactionComDadosInconsistentesAmountNull() throws Exception {
		TransactionDTO dto = new TransactionDTO();
		dto.setAccountId(1L);
		dto.setOperationTypeId(1L);

		testSaveTransactionComDadosInconsistente(dto);
	}

	@Test
	public void testSaveTransactionComDadosInconsistentesOperationTypeIdNull() throws Exception {
		TransactionDTO dto = new TransactionDTO();
		dto.setAmount(new BigDecimal(1));
		dto.setOperationTypeId(1L);

		testSaveTransactionComDadosInconsistente(dto);
	}

	@Test
	public void testSaveTransactionComSucesso() throws Exception {
		TransactionDTO dto = new TransactionDTO();
		dto.setAccountId(1L);
		dto.setAmount(new BigDecimal(1));
		dto.setOperationTypeId(4L);

		mockMvc.perform(post("/api/v1/transactions").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(dto))).andExpect(status().isCreated())
		.andExpect(content().json("{\"amount\":1,\"account_id\":1,\"operation_type_id\":4,\"transaction_id\":1}"));
	}
	

	private void testSaveTransactionComDadosInconsistente(TransactionDTO dto) throws Exception {
		mockMvc.perform(post("/api/v1/transactions").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(dto))).andExpect(status().isBadRequest())
				.andExpect(content().json(
						"{\"status\":400,\"detail\":\"Invalid request.\",\"developerMessage\":\"br.com.pismo.exception.RequisicaoInvalidaException\"}"));
	}

	private String convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper Obj = new ObjectMapper();
		return Obj.writeValueAsString(object);
	}
}
