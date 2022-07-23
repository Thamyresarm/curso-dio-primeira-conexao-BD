package org.example;

import org.example.config.AlunoDAO;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        AlunoDAO alunoDAO = new AlunoDAO();

        // =========================== 1 - Consulta =================================================
        List<Aluno> alunos = alunoDAO.list();

        alunos.stream().forEach(System.out::println);

        // ======================= 1.1 - Consulta com filtro ========================================
        Aluno alunoParaConsulta = alunoDAO.getById(1);

        System.out.println(alunoParaConsulta);


        // =========================== 2 - Inserção =================================================
        Aluno alunoParaInsercao = new Aluno(
                "Matheus",
                43,
                "SP"
        );

        alunoDAO.create(alunoParaInsercao);

        alunoDAO.list().stream().forEach(System.out::println);
        // =========================== 3 - Delete ===================================================
        alunoDAO.delete(5);

        alunoDAO.list().stream().forEach(System.out::println);
        // =========================== 4 - Atualizar ================================================

        Aluno alunoParaAtualizar = alunoDAO.getById(3);
        alunoParaAtualizar.setNome("Joaquim");
        alunoParaAtualizar.setIdade(18);
        alunoParaAtualizar.setEstado("RS");

        alunoDAO.update(alunoParaAtualizar);
        alunoDAO.list().stream().forEach(System.out::println);
    }
}
