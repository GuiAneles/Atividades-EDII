
public class ArvoreRubroNegra {

    private NoRubroNegro raiz;
    private static final boolean VERMELHO = true;
    private static final boolean PRETO = false;

    public void inserir(int valor) {
        NoRubroNegro no = new NoRubroNegro(valor);
        if (raiz == null) {
            raiz = no;
        } else {
            inserirNo(raiz, no);
        }
        balancearAposInsercao(no);
    }

    private void inserirNo(NoRubroNegro noPai, NoRubroNegro novoNo) {
        if (novoNo.valor < noPai.valor) {
            if (noPai.esquerda == null) {
                noPai.esquerda = novoNo;
                novoNo.pai = noPai;
            } else {
                inserirNo(noPai.esquerda, novoNo);
            }
        } else {
            if (noPai.direita == null) {
                noPai.direita = novoNo;
                novoNo.pai = noPai;
            } else {
                inserirNo(noPai.direita, novoNo);
            }
        }
    }

    private void balancearAposInsercao(NoRubroNegro no) {
        if (no.pai == null) {
            no.ehVermelho = PRETO;
            return;
        }
        if (!no.pai.ehVermelho) {
            return;
        }

        NoRubroNegro avo = no.pai.pai;
        NoRubroNegro tio = obterTio(no);

        if (tio != null && tio.ehVermelho) {
            no.pai.ehVermelho = PRETO;
            tio.ehVermelho = PRETO;
            avo.ehVermelho = VERMELHO;
            balancearAposInsercao(avo);
            return;
        }

        if (no == no.pai.direita && no.pai == avo.esquerda) {
            rotacaoEsquerda(no.pai);
            no = no.esquerda;
        } else if (no == no.pai.esquerda && no.pai == avo.direita) {
            rotacaoDireita(no.pai);
            no = no.direita;
        }

        no.pai.ehVermelho = PRETO;
        if (avo != null) {
            avo.ehVermelho = VERMELHO;
            if (no == no.pai.esquerda) {
                rotacaoDireita(avo);
            } else {
                rotacaoEsquerda(avo);
            }
        }
    }

    public void remover(int valor) {
        NoRubroNegro noParaRemover = buscar(this.raiz, valor);
        if (noParaRemover != null) {
            removerNo(noParaRemover);
        }
    }

    private void removerNo(NoRubroNegro no) {
        NoRubroNegro y = no;
        NoRubroNegro x;
        boolean corOriginalY = y.ehVermelho;

        if (no.esquerda == null) {
            x = no.direita;
            transplantar(no, no.direita);
        } else if (no.direita == null) {
            x = no.esquerda;
            transplantar(no, no.esquerda);
        } else {
            y = encontrarMenor(no.direita);
            corOriginalY = y.ehVermelho;
            x = y.direita;
            if (x != null && y.pai == no) {
                x.pai = y;
            } else {
                transplantar(y, y.direita);
                y.direita = no.direita;
                if (y.direita != null) {
                    y.direita.pai = y;
                }
            }
            transplantar(no, y);
            y.esquerda = no.esquerda;
            y.esquerda.pai = y;
            y.ehVermelho = no.ehVermelho;
        }

        if (!corOriginalY) {
            balancearAposRemocao(x);
        }
    }

    private void balancearAposRemocao(NoRubroNegro no) {
        while (no != null && no != raiz && !no.ehVermelho) {
            if (no.pai != null && no == no.pai.esquerda) {
                NoRubroNegro irmao = no.pai.direita;
                if (irmao != null) {
                    if (irmao.ehVermelho) {
                        irmao.ehVermelho = PRETO;
                        no.pai.ehVermelho = VERMELHO;
                        rotacaoEsquerda(no.pai);
                        irmao = no.pai.direita;
                    }
                    if (irmao != null && (irmao.esquerda == null || !irmao.esquerda.ehVermelho) && (irmao.direita == null || !irmao.direita.ehVermelho)) {
                        irmao.ehVermelho = VERMELHO;
                        no = no.pai;
                    } else {
                        if (irmao != null && (irmao.direita == null || !irmao.direita.ehVermelho)) {
                            if (irmao.esquerda != null) {
                                irmao.esquerda.ehVermelho = PRETO;
                            }
                            irmao.ehVermelho = VERMELHO;
                            rotacaoDireita(irmao);
                            irmao = no.pai.direita;
                        }
                        if (irmao != null) {
                            irmao.ehVermelho = no.pai.ehVermelho;
                            no.pai.ehVermelho = PRETO;
                            if (irmao.direita != null) {
                                irmao.direita.ehVermelho = PRETO;
                            }
                            rotacaoEsquerda(no.pai);
                        }
                        no = raiz;
                    }
                } else {
                    no = no.pai;
                }
            } else if (no.pai != null) {
                NoRubroNegro irmao = no.pai.esquerda;
                if (irmao != null) {
                    if (irmao.ehVermelho) {
                        irmao.ehVermelho = PRETO;
                        no.pai.ehVermelho = VERMELHO;
                        rotacaoDireita(no.pai);
                        irmao = no.pai.esquerda;
                    }
                    if (irmao != null && (irmao.direita == null || !irmao.direita.ehVermelho) && (irmao.esquerda == null || !irmao.esquerda.ehVermelho)) {
                        irmao.ehVermelho = VERMELHO;
                        no = no.pai;
                    } else {
                        if (irmao != null && (irmao.esquerda == null || !irmao.esquerda.ehVermelho)) {
                            if (irmao.direita != null) {
                                irmao.direita.ehVermelho = PRETO;
                            }
                            irmao.ehVermelho = VERMELHO;
                            rotacaoEsquerda(irmao);
                            irmao = no.pai.esquerda;
                        }
                        if (irmao != null) {
                            irmao.ehVermelho = no.pai.ehVermelho;
                            no.pai.ehVermelho = PRETO;
                            if (irmao.esquerda != null) {
                                irmao.esquerda.ehVermelho = PRETO;
                            }
                            rotacaoDireita(no.pai);
                        }
                        no = raiz;
                    }
                } else {
                    no = no.pai;
                }
            } else {
                break;
            }
        }
        if (no != null) {
            no.ehVermelho = PRETO;
        }
    }

    private void transplantar(NoRubroNegro u, NoRubroNegro v) {
        if (u.pai == null) {
            raiz = v;
        } else if (u == u.pai.esquerda) {
            u.pai.esquerda = v;
        } else {
            u.pai.direita = v;
        }
        if (v != null) {
            v.pai = u.pai;
        }
    }

    private NoRubroNegro encontrarMenor(NoRubroNegro no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    private void rotacaoEsquerda(NoRubroNegro no) {
        if (no == null || no.direita == null) {
            return;
        }
        NoRubroNegro novoPai = no.direita;
        no.direita = novoPai.esquerda;
        if (novoPai.esquerda != null) {
            novoPai.esquerda.pai = no;
        }
        novoPai.pai = no.pai;
        if (no.pai == null) {
            this.raiz = novoPai;
        } else if (no == no.pai.esquerda) {
            no.pai.esquerda = novoPai;
        } else {
            no.pai.direita = novoPai;
        }
        novoPai.esquerda = no;
        no.pai = novoPai;
    }

    private void rotacaoDireita(NoRubroNegro no) {
        if (no == null || no.esquerda == null) {
            return;
        }
        NoRubroNegro novoPai = no.esquerda;
        no.esquerda = novoPai.direita;
        if (novoPai.direita != null) {
            novoPai.direita.pai = no;
        }
        novoPai.pai = no.pai;
        if (no.pai == null) {
            this.raiz = novoPai;
        } else if (no == no.pai.direita) {
            no.pai.direita = novoPai;
        } else {
            no.pai.esquerda = novoPai;
        }
        novoPai.direita = no;
        no.pai = novoPai;
    }

    private NoRubroNegro obterTio(NoRubroNegro no) {
        if (no.pai == null || no.pai.pai == null) {
            return null;
        }
        if (no.pai == no.pai.pai.esquerda) {
            return no.pai.pai.direita;
        } else {
            return no.pai.pai.esquerda;
        }
    }

    private NoRubroNegro buscar(NoRubroNegro no, int valor) {
        if (no == null || no.valor == valor) {
            return no;
        }
        if (valor < no.valor) {
            return buscar(no.esquerda, valor);
        }
        return buscar(no.direita, valor);
    }

    public int contar(int valor) {
        NoRubroNegro noEncontrado = buscar(this.raiz, valor);
        return (noEncontrado != null) ? 1 : 0;
    }
}
