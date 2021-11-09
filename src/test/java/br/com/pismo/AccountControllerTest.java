package br.com.pismo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

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

import br.com.pismo.dto.AccountDTO;
import br.com.pismo.model.Account;
import br.com.pismo.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@ActiveProfiles("test")
public class AccountControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AccountRepository repository;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		Account account = new Account();
    	account.setDocumentNumber("12345678900");
    	repository.save(account);
	}
	
    @Test
    public void testSaveAccountComDadosInconsistentesNumeroDocumentoNull()throws Exception {
    	AccountDTO dto = new AccountDTO();
        dto.setDocumentNumber(null);

        testSaveAccountComDadosInconsistente(dto);
    }

    @Test
    public void testSaveAccountComDadosInconsistentesNumeroDocumentoVazio()throws Exception {
    	AccountDTO dto = new AccountDTO();
    	dto.setDocumentNumber("");

        testSaveAccountComDadosInconsistente(dto);
    }


    @Test
    public void testSaveAccountComSucesso()throws Exception {
    	AccountDTO dto = new AccountDTO();
    	dto.setDocumentNumber("20211100");

        mockMvc.perform(post("/api/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dto)))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void testFindAccountByIDComSucesso()throws Exception {
        mockMvc.perform(get("/api/v1/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"account_id\":1,\"document_number\":\"12345678900\"}"));
    }
    
    @Test
    public void testFindAccountByIDNotFound()throws Exception {
        mockMvc.perform(get("/api/v1/accounts/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Account not found."));
    }

	private void testSaveAccountComDadosInconsistente(AccountDTO dto) throws Exception {
		mockMvc.perform(
				post("/api/v1/accounts").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(dto)))
				.andExpect(status().isBadRequest()).andExpect(content().json(
						"{\"status\":400,\"detail\":\"Invalid request.\",\"developerMessage\":\"br.com.pismo.exception.RequisicaoInvalidaException\"}"));
	}

	private String convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper Obj = new ObjectMapper();
		return Obj.writeValueAsString(object);
	}
}
