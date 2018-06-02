package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.Aluno;
import com.equipeRL.backend.Repositories.AlunosRepository;
import com.equipeRL.backend.Services.exceptions.ItemDuplicadoException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Essa é a classe de Serviço do Aluno, que contém os métodos responsáveis pelo CRUD desse objeto no banco de dados.
 * @author EquipeACL
 *
 */
@Service
public class AlunoService {

//	private static Logger logger = Logger.getLogger(AlunoService.class);
//
//	@Autowired
//	private AlunosRepository alunosRepository;
//
//	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//	@PersistenceContext
//    private EntityManager manager;
//
//	/**
//	 * Esse é o método responsável por salvar um objeto no banco de dados
//	 * @param aluno, que é o objeto que irá ser salvo no banco de dados.
//	 */
//	@Transactional
//	public Aluno salvar (Aluno aluno) {
//
//		Aluno entAluno = new Aluno(aluno);
//
//		Optional <Aluno> alunoOptional = alunosRepository.findByCpfIgnoreCase(aluno.getCpf());
//
//		if(alunoOptional.isPresent()){
//			throw new ItemDuplicadoException(" Aluno(a) já Cadastrado!");
//		}
//
//
//		entAluno.setSenha(this.passwordEncoder.encode(entAluno.getSenha()));
//		entAluno.setConfirmacaoSenha(entAluno.getSenha());
//		try {
//			return alunosRepository.saveAndFlush(entAluno);
//		} catch (Exception e) {
//			logger.error("Erro ao cadastrar aluno.",e);
//			return null;
//		}
//	}
//
//	public List<Aluno> getAllAlunos() {
//
//		return alunosRepository.findAll();
//
//	}
//
//	/**
//	 * Esse é o método responsável por fazer uma busca por nome no banco de dados
//	 * @param busca, que é a String que contém o parâmetro de busca por Aluno no banco de dados
//	 * @return List<Aluno> contendo o(s) objeto(s) referentes à busca
//	 */
//	@Transactional
//	public List<Aluno> buscarPorNome (String busca) {
//		return manager.createQuery("select a from Aluno a where a.nome like '%"+busca+"%'", Aluno.class).getResultList();
//	}
//
//
//	@Transactional
//	public boolean atualizar(Aluno aluno) {
//
//		Aluno entAluno = new Aluno(aluno);
//
//		entAluno.setSenha(this.passwordEncoder.encode(entAluno.getSenha()));
//		entAluno.setConfirmacaoSenha(entAluno.getSenha());
//		try {
//			alunosRepository.save(entAluno);
//			logger.info("Aluno atualizado com sucesso.");
//			return true;
//		} catch (Exception e) {
//			logger.error("Erro ao atualizar aluno.",e);
//			return false;
//		}
//	}
//
//	@Transactional
//	public boolean remover(Long id) {
//		if(id>0) {
//			try {
//				alunosRepository.deleteById(id);
//				logger.info("Aluno removido com sucesso.");
//				return true;
//			} catch (Exception e) {
//				logger.error("Erro ao remover aluno.",e);
//
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * Esse é o método responsável por fornecer as permissões a um objeto do tipo Aluno
//	 * @param aluno, que é o objeto cujo irá ter as permissões atribuídas a si
//	 * @return List<String> contendo as permissões referentes ao objeto passado por parâmetro
//	 */
//	public List<String> permissoes(Aluno aluno) {
//		return manager.createQuery("select distinct p.nome from Aluno a inner join a.grupos g inner join g.permissoes p where a = :aluno",String.class)
//				.setParameter("aluno", aluno)
//				.getResultList();
//	}
}
