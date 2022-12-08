package tech.devinhouse.labscholl2.exception;

    public class RegistroExistenteException extends RuntimeException {

        public RegistroExistenteException(String nomeRecurso, Integer codigo) {
            super(nomeRecurso +  " com codigo "  + codigo +   " já existente!");
        }


        public RegistroExistenteException(String aluno, Long cpf) {
            super(aluno +  " com CPF "  + cpf +   " já existente!");
        }
    }

