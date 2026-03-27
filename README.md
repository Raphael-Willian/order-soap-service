🚀 Spring SOAP API - Order Management
📌 Overview

Implementação de uma API SOAP utilizando Spring Boot, baseada em contrato XSD, com foco em:

Exposição de endpoints SOAP
Serialização/deserialização XML via JAXB
Geração automática de WSDL
Estruturação baseada em contrato (contract-first)
🧠 Arquitetura

Fluxo de execução:

SOAP Request (XML)
        ↓
MessageDispatcherServlet (/ws)
        ↓
@Endpoint (PayloadRoot)
        ↓
Service Layer
        ↓
SOAP Response (XML)
🏗️ Estrutura do projeto
src/main/java/com/raphael/ordersoap

├── config/        # Configuração SOAP (Servlet, WSDL, JAXB)
├── endpoint/      # Entry point SOAP (@Endpoint)
├── service/       # Regra de negócio

src/main/resources

└── xsd/           # Contrato da API (XSD)
📄 Contrato (XSD)

Define o contrato de entrada e saída da API.

Namespace:

http://raphael.com/ordersoap
Requests
<createOrderRequest>
    <product>Notebook</product>
    <quantity>2</quantity>
</createOrderRequest>
<getOrderRequest>
    <product>Notebook</product>
</getOrderRequest>
Responses
<createOrderResponse>
    <message>Pedido criado com sucesso</message>
</createOrderResponse>
<getOrderResponse>
    <message>Pedido encontrado</message>
</getOrderResponse>
🔌 Endpoint

Responsável por mapear o XML para métodos Java.

Exemplo:

@PayloadRoot(namespace = NAMESPACE, localPart = "getOrderRequest")
@ResponsePayload
public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request)
Anotações
@Endpoint → define classe como handler SOAP
@PayloadRoot → mapeia XML (namespace + localPart)
@RequestPayload → XML → objeto Java
@ResponsePayload → objeto Java → XML
⚙️ Configuração SOAP
Servlet
return new ServletRegistrationBean<>(servlet, "/ws/*");

Endpoint base:

/ws
WSDL
@Bean(name = "orders")
public DefaultWsdl11Definition wsdl11Definition(XsdSchema schema)

Disponível em:

http://localhost:8080/ws/orders.wsdl
JAXB (binding XML)
marshaller.setContextPath("com.raphael.ordersoap");

Responsável por:

Unmarshal: XML → Java
Marshal: Java → XML
🧪 Teste via Postman
Endpoint
POST http://localhost:8080/ws
Headers
Content-Type: text/xml
getOrder
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ord="http://raphael.com/ordersoap">
   <soapenv:Header/>
   <soapenv:Body>
      <ord:getOrderRequest>
         <ord:product>Notebook</ord:product>
      </ord:getOrderRequest>
   </soapenv:Body>
</soapenv:Envelope>
createOrder
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ord="http://raphael.com/ordersoap">
   <soapenv:Header/>
   <soapenv:Body>
      <ord:createOrderRequest>
         <ord:product>Notebook</ord:product>
         <ord:quantity>2</ord:quantity>
      </ord:createOrderRequest>
   </soapenv:Body>
</soapenv:Envelope>
⚠️ Problemas comuns
Content-Type incorreto
application/json ❌
text/xml        ✅
Namespace divergente

Deve ser exatamente:

http://raphael.com/ordersoap
Case-sensitive (XML)
getOrderRequest  ✅
GetOrderRequest  ❌
JAXB runtime ausente

Erro típico:

JAXBException

Solução: adicionar dependência jaxb-runtime

🎯 Objetivo do projeto

Este projeto demonstra:

Uso de SOAP com Spring Boot
Modelagem contract-first (XSD)
Integração baseada em XML
Configuração real de infraestrutura SOAP

🚀 Evoluções possíveis
Persistência com banco (JPA)
Versionamento de contrato (XSD/WSDL)
Integração com REST APIs
Mensageria (RabbitMQ / Kafka)
Segurança (WS-Security)
📌 Observação

SOAP ainda é amplamente utilizado em:

Sistemas legados corporativos
Integrações bancárias
ERPs
👨‍💻 Autor

Raphael — foco em backend e integrações.


Se esse projeto te ajudou, considere deixar uma estrela no repositório ⭐.
