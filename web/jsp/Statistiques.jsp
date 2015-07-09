<%@page import="oracle.acsi.Article"%>
<%@page import="oracle.acsi.VisiteManager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="oracle.acsi.InscriptionManager"%>
<%@include file="header.jsp" %>
<%
InscriptionManager im = InscriptionManager.getInstance();
VisiteManager vm = VisiteManager.getInstance();
//on récupère le premier jour du mois
Calendar c = Calendar.getInstance();   // date du jour
c.set(Calendar.DAY_OF_MONTH, 1);
Date premJourMois = c.getTime();
%>
        <h1>Statistiques</h1>
        <div>
            Nombre total d'inscrits : <% out.print(im.getNbInscrits()); %>
            <br><br>
            Nombre de nouveaux inscrits (ce mois-ci): <% out.print(im.getInscritsDepuis(premJourMois)); %>
            <br><br>
            Top 3 des codes postaux : &emsp;
            <%
            List<String> cpListe = im.getTopCp();
            for(int i=0; i<cpListe.size(); i++){
                out.print((i+1) +") "+cpListe.get(i)+"&emsp;");
            }
            %>
            <br><br>
            Top 3 des articles les plus vus : &emsp;
            <%
            List<Article> hitParade = vm.getHitParade(3);
            for(int i=0; i<hitParade.size(); i++){
                out.print((i+1) +") "+hitParade.get(i).getLibelle() +"&emsp;");
            }
            %>
            
        </div>
<%@include file="footer.jsp" %>